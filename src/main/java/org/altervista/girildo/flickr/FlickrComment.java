package org.altervista.girildo.flickr;

import org.altervista.girildo.Agent;

import java.time.Instant;

class FlickrComment {


    private final String text;
    private final Agent author;
    private final Instant dateOfCreation;

    FlickrComment (String text, Agent author, Instant dateOfCreation)
    {
        this.text = text;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
    }

    public String getText() {
        return text;
    }

    public Agent getAuthor() {
        return author;
    }

    public Instant getDateOfCreation() {
        return dateOfCreation;
    }
}
