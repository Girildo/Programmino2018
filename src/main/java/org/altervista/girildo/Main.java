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


        t.addVote(new Vote(ph[0], "T", 1));
        t.addVote(new Vote(ph[1], "E", 1));
        t.addVote(new Vote(ph[0], "E", 1));
        t.addVote(new Vote(ph[1], "C", 1));
        t.addVote(new Vote(ph[0], "T", 1));

        t.computeTable();

        FlickrProvider provider = new FlickrProvider() {
            public List<Vote> provideVotes() {
                return null;
            }
        };

    }
}
