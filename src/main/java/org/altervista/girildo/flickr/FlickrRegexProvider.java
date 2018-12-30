package org.altervista.girildo.flickr;

import com.flickr4java.flickr.FlickrException;
import org.altervista.girildo.Vote;
import org.altervista.girildo.Voteable;
import org.altervista.girildo.exceptions.InvalidVoteException;
import org.altervista.girildo.util.HelperMethods;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class FlickrRegexProvider extends FlickrProvider {

    public FlickrRegexProvider(String flickrUrl) {
        super(flickrUrl);
    }


    @Override
    public final List<Voteable> provideVoteables() throws Exception {

        throw new NotImplementedException();
    }

    @Override
    public final List<Vote> provideVotes() throws InvalidVoteException, IllegalStateException {
        ArrayList<Vote> toReturn = new ArrayList<>();

        HashMap<String, Integer> pointScheme = this.getPointScheme();

        Pattern pattern = this.compileVotesRegexPattern();

        try {
            for (FlickrComment comment : super.getListOfComments()) {
                Matcher matcher = pattern.matcher(comment.getText());
                if (!matcher.matches())
                    continue;

                for (int i = 1; i < matcher.groupCount(); i += 2) {
                    if (matcher.start(i) != -1) {
                        // Only happening for groups that are actually matched.
                        Integer pointNum = pointScheme.get(matcher.group(i));
                        if (pointNum == null)
                            pointNum = pointScheme.get(Integer.toString(i % 2));
                        pointNum = pointNum.intValue();

                        Vote v = new Vote(
                                comment.getAuthor(),
                                matcher.group(i),     // The category
                                matcher.group(i + 1), // The voteable itself
                                pointNum);   // The points
                        toReturn.add(v);
                    }
                }
            }
        }
        catch (FlickrException | IllegalStateException ex)
        {
            throw new IllegalStateException("Non sono riuscito a recuperare i commenti da Flickr, dev'esserci" +
                    "un problema col sito", ex);
        }
        return toReturn;
    }

    /**
     * Builds the regex pattern given the categories and all their possible permutations.
     * Odd matching groups contain the voting categories, if they exists.
     * Even matching groups contain the photo ID, in any case.
     *
     * @return The {@link Pattern} matching the categories specified in the JSON file.
     */
    private Pattern compileVotesRegexPattern() {
        List<String> categories = this.getCategoriesName();
        StringBuilder builder = new StringBuilder("(?:.+)?"); //This matches random characters without capturing

        ArrayList<List<String>> permutations = new ArrayList<>();
        HelperMethods.permuteList(categories.toArray(new String[]{}), categories.size(), permutations);
        permutations = (ArrayList<List<String>>) permutations.stream().distinct().collect(Collectors.toList());


        builder.append("(?:");
        for (List<String> permutation : permutations) {
            builder.append("(?:");
            for (String c : permutation) {
                builder.append(String.format("(?:(%s) ?#(\\d{1,}))(?:.+)?", c));
            }
            builder.append(")");
            builder.append("|"); //Alternatives for permutations.
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        builder.append("(?:.+)?"); //This matches random characters without capturing
        return Pattern.compile(builder.toString(), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    }


    abstract List<String> getCategoriesName();

    abstract HashMap<String, Integer> getPointScheme();
}
