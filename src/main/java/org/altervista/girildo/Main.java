package org.altervista.girildo;

import org.altervista.girildo.flickr.FlickrJSONProvider;
import org.altervista.girildo.flickr.FlickrProvider;

public class Main {

    public static void main(String[] args) {
        Table t = new Table();

        Photo[] ph = new Photo[]{new Photo(0), new Photo(1), new Photo(2)};

        t.addVoteable(ph[0]);
        t.addVoteable(ph[1]);
        t.addVoteable(ph[2]);

        try {
            FlickrProvider provider = new FlickrJSONProvider(
                    "https://www.flickr.com/groups/clickthecontest/discuss/72157704704255574/",
                    "/ClickTheContestRules.json");
            provider.provideVotes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        t.computeTable();


    }
}
