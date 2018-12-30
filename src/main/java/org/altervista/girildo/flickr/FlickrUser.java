package org.altervista.girildo.flickr;

import org.altervista.girildo.Agent;

public class FlickrUser implements Agent {

    private final String name;
    private final int ID;

    FlickrUser(String name) {
        this(name, name.hashCode());
    }

    private FlickrUser(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.ID;
    }
}
