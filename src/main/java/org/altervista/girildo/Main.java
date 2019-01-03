package org.altervista.girildo;

import org.altervista.girildo.flickr.FlickrJSONProvider;
import org.altervista.girildo.flickr.FlickrProvider;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TableComputer cmpt;
        Table table = null;
        try {
            FlickrProvider provider = new FlickrJSONProvider(
                    "https://www.flickr.com/groups/clickthecontest/discuss/72157677011628148/",
                    "/ClickTheContestRules.json");
                    //"https://www.flickr.com/groups/1744262@N24/discuss/72157698919350640/",
                    //"/SoniaGalleryRules.json");
            //List<Voteable> photoList =  provider.provideVoteables();
            //List<Vote> voteList = provider.provideVotes();
            cmpt = new TableComputer(provider, provider);
            table = cmpt.generateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(table.toString());

    }
}
