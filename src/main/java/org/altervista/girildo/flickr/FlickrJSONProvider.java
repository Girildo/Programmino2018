package org.altervista.girildo.flickr;

import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class FlickrJSONProvider extends FlickrRegexProvider {

    private final static JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();

    public FlickrJSONProvider(String url, String jsonResource) throws IOException {
        super(url);
        File rulesFile = new File(FlickrJSONProvider.class.getResource(jsonResource).getFile());
        JACKSON_FACTORY.fromReader(new FileReader(rulesFile), HashMap.class);
    }

    @Override
    List<String> getCategoriesName() {
        return null;
    }

    @Override
    List<Integer> getPointScheme() {
        return null;
    }
}