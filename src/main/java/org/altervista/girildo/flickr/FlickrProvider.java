package org.altervista.girildo.flickr;

import org.altervista.girildo.Vote;
import org.altervista.girildo.VoteProvider;
import org.altervista.girildo.VoteableProvider;

import java.util.List;
import java.util.regex.Pattern;

public abstract class FlickrProvider implements VoteProvider, VoteableProvider {
    private final FlickrInterface flickrInterface = FlickrInterface.getInstance();
    private final String URL;

    private List<FlickrComment> listOfComments;


    protected FlickrInterface getFlickrInterface() {
        return flickrInterface;
    }

    private List<FlickrComment> getListOfComments() throws Exception{
        if(this.listOfComments == null)
            this.listOfComments = flickrInterface.getCommentsFromDiscussion(this.URL);
        return this.listOfComments;
    }

    public FlickrProvider(String url) {
        this.URL = url;
    }


    private Pattern compileVotesRegexPattern() {
        List<String> categories = this.getCategoriesName();
        StringBuilder builder = new StringBuilder("(?:.+)?");
        for (String c : categories) {
            builder.append(String.format("(?:(%s) ?#(\\d{1,}))(?:.+)?", c));
        }

        return Pattern.compile(builder.toString());
    }

    @Override
    public final List<Vote> provideVotes() {
        Pattern compileVotesRegexPattern()
        for(FlickrComment )
    }

    abstract List<String> getCategoriesName();
    public abstract List<Integer> getPointScheme();
}

