package org.altervista.girildo.flickr;

import org.altervista.girildo.Agent;

class FlickrComment {


    private final String text;
    private final Agent author;

    FlickrComment (String text, Agent author)
    {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public Agent getAuthor() {
        return author;
    }
}
