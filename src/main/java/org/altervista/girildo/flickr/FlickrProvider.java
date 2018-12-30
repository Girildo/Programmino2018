package org.altervista.girildo.flickr;

import com.flickr4java.flickr.FlickrException;
import org.altervista.girildo.VoteProvider;
import org.altervista.girildo.VoteableProvider;
import org.altervista.girildo.exceptions.InvalidVoteException;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

public abstract class FlickrProvider implements VoteProvider, VoteableProvider {
    private final FlickrInterface flickrInterface = FlickrInterface.getInstance();
    private final String flickrURL;

    private List<FlickrComment> listOfComments;



    protected FlickrInterface getFlickrInterface() {
        return flickrInterface;
    }

    List<FlickrComment> getListOfComments() throws FlickrException, InvalidStateException {
        return getListOfComments(false);
    }

    List<FlickrComment> getListOfComments(boolean forceFetch)  throws FlickrException, InvalidStateException {
        if (this.listOfComments == null || forceFetch)
            this.listOfComments = flickrInterface.getCommentsFromDiscussion(this.flickrURL);
        return this.listOfComments;
    }

    public FlickrProvider(String flickrUrl) {
        this.flickrURL = flickrUrl;
    }
}

