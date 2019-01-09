package org.altervista.girildo;

import org.altervista.girildo.flickr.FlickrJSONProvider;
import org.altervista.girildo.flickr.FlickrProvider;
import org.altervista.girildo.table.TableComputer;

public class Main {

    public static void main(String[] args) {
        TableComputer cmpt;
        try {
            FlickrProvider provider = new FlickrJSONProvider(
                    "https://www.flickr.com/groups/clickthecontest/discuss/72157677011628148/",
                    "/ClickTheContestRules.json");
                    //"https://www.flickr.com/groups/1744262@N24/discuss/72157698919350640/",
                    //"/SoniaGalleryRules.json");
            //List<Voteable> photoList =  provider.provideVoteables();
            //List<Vote> voteList = provider.provideVotes();
            cmpt = new TableComputer(provider, provider);
            System.out.print(cmpt.generateTable().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
