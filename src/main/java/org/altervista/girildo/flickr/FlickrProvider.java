package org.altervista.girildo.flickr;

import com.flickr4java.flickr.FlickrException;
import org.altervista.girildo.VoteProvider;
import org.altervista.girildo.VoteableProvider;

import java.util.List;

public abstract class FlickrProvider implements VoteProvider, VoteableProvider {
    private final FlickrInterface flickrInterface = FlickrInterface.getInstance();
    private final String flickrURL;

    private List<FlickrComment> listOfComments;


    List<FlickrComment> getCommentList() throws FlickrException, IllegalStateException {
        return getCommentList(false);
    }


    List<FlickrComment> getCommentList(boolean forceFetch) throws FlickrException, IllegalStateException {
        if (this.listOfComments == null || forceFetch)
            this.listOfComments = flickrInterface.getCommentsFromDiscussion(this.flickrURL);

        return this.listOfComments;
    }


    FlickrProvider(String flickrUrl) {
        this.flickrURL = flickrUrl;
    }
}

