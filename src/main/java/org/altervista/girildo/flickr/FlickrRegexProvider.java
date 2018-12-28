package org.altervista.girildo.flickr;

import org.altervista.girildo.Vote;
import org.altervista.girildo.Voteable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FlickrRegexProvider extends FlickrProvider {

    public FlickrRegexProvider(String flickrUrl) {
        super(flickrUrl);
    }


    @Override
    public final List<Voteable> provideVoteables() throws Exception {

        throw new NotImplementedException();
    }

    @Override
    public final List<Vote> provideVotes() throws Exception {
        ArrayList<Vote> toReturn = new ArrayList<>();

        Pattern pattern = compileVotesRegexPattern();
        for (FlickrComment comment : super.getListOfComments()) {
            Matcher matcher = pattern.matcher(comment.getText());
            if(!matcher.matches())
                continue;

            for(int i = 0; i < matcher.groupCount(); i++){
                System.out.println(matcher.group(i));
            }
        }

        return null;
    }

    private Pattern compileVotesRegexPattern() {
        List<String> categories = this.getCategoriesName();
        StringBuilder builder = new StringBuilder("(?:.+)?");
        for (String c : categories) {
            builder.append(String.format("(?:(%s) ?#(\\d{1,}))(?:.+)?", c));
        }

        return Pattern.compile(builder.toString());
    }

    abstract List<String> getCategoriesName();
    abstract List<Integer> getPointScheme();
}
