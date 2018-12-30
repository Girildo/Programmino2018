package org.altervista.girildo.flickr;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlickrJSONProvider extends FlickrRegexProvider {

    private final static JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final HashMap map;

    public FlickrJSONProvider(String url, String jsonResource) throws IOException {
        super(url);
        File rulesFile = new File(FlickrJSONProvider.class.getResource(jsonResource).getFile());
        map = JACKSON_FACTORY.fromReader(new FileReader(rulesFile), HashMap.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    List<String> getCategoriesName() {
        ArrayMap votingCategories = (ArrayMap) map.get("voting-categories");
        int votesCount = ((BigDecimal) votingCategories.get("count")).intValue();


        if (votingCategories.get("named").equals(true))
            return new ArrayList<>((Collection<String>) (votingCategories.get("names")));
        else {
            String[] strings = new String[votesCount];
            Arrays.fill(strings, "");
            return Arrays.asList(strings);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    HashMap<String, Integer> getPointScheme() {
        HashMap<String, Integer> toReturn = new HashMap<>();
        ArrayMap votingCategories = (ArrayMap) map.get("voting-categories");
        int votesCount = ((BigDecimal) votingCategories.get("count")).intValue();

        ArrayList<BigDecimal> voteValues = (ArrayList<BigDecimal>) votingCategories.get("values");

        ArrayList<String> categoryNames;

        if (votingCategories.get("named").equals(true))
            categoryNames = new ArrayList<>((Collection<String>) (votingCategories.get("names")));
        else {
            categoryNames = (ArrayList<String>) IntStream.range(1, votesCount + 1).
                    mapToObj(String::valueOf).
                    collect(Collectors.toList());
        }

        for (String s:categoryNames)
        {
            toReturn.put(s.toLowerCase(), voteValues.get(categoryNames.indexOf(s)).intValue());
        }
        return toReturn;
    }
}