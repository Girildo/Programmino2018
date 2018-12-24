package org.altervista.girildo.flickr;

class FlickrComment {

    private final String authorName;
    private final String text;

    FlickrComment (String text, String authorName)
    {
        this.text = text;
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public String getAuthorName() {
        return authorName;
    }
}
