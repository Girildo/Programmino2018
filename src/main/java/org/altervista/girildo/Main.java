package org.altervista.girildo;

import org.altervista.girildo.flickr.FlickrProvider;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Table t = new Table();

        Photo[] ph = new Photo[]{new Photo(0), new Photo(1), new Photo(2)};

        t.addVoteable(ph[0]);
        t.addVoteable(ph[1]);
        t.addVoteable(ph[2]);



        t.computeTable();


    }
}
