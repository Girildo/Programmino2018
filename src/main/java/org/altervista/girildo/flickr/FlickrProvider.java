package org.altervista.girildo.flickr;

import org.altervista.girildo.VoteProvider;
import org.altervista.girildo.VoteableProvider;

import java.util.List;

public abstract class FlickrProvider implements VoteProvider, VoteableProvider {
    private final FlickrInterface flickrInterface = FlickrInterface.getInstance();
    private final String flickrURL;

    private List<FlickrComment> listOfComments;



    protected FlickrInterface getFlickrInterface() {
        return flickrInterface;
    }

    List<FlickrComment> getListOfComments() throws Exception {
        if (this.listOfComments == null)
            this.listOfComments = flickrInterface.getCommentsFromDiscussion(this.flickrURL);
        return this.listOfComments;
    }


    public FlickrProvider(String flickrUrl) {
        this.flickrURL = flickrUrl;
    }


}

