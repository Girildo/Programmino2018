package org.altervista.girildo.flickr;

import com.flickr4java.flickr.FlickrException;
import org.altervista.girildo.Photo;
import org.altervista.girildo.Vote;
import org.altervista.girildo.Voteable;
import org.altervista.girildo.exceptions.InvalidVoteException;
import org.altervista.girildo.util.HelperMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class FlickrRegexProvider extends FlickrProvider {

    FlickrRegexProvider(String flickrUrl) {
        super(flickrUrl);
    }

    @Override
    public final List<Voteable> provideVoteables() throws IllegalStateException {
        ArrayList<Voteable> toReturn = new ArrayList<>();
        Pattern voteablePattern = Pattern.compile(this.getVoteablePatternString(), Pattern.DOTALL);
        Pattern startVotingPattern = Pattern.compile(this.getStartVotingPatternString(), Pattern.DOTALL);

        try {
            List<FlickrComment> filteredComments = super.getCommentList()
                    .stream()
                    .filter(c -> !(startVotingPattern.matcher(c.getText()).matches()))
                    .filter(c -> voteablePattern.matcher(c.getText()).matches())
                    .collect(Collectors.toList());

            for (FlickrComment comment : filteredComments) {
                Matcher matcher = voteablePattern.matcher(comment.getText());
                matcher.find();
                Voteable vtbl = new Photo(Integer.parseInt(matcher.group(1)),
                        comment.getAuthor());

                toReturn.add(vtbl);
            }
        } catch (FlickrException | IllegalStateException ex) {
            throw new IllegalStateException("Non sono riuscito a recuperare i commenti da Flickr, dev'esserci" +
                    "un problema col sito", ex);
        }
        return toReturn;
    }


    @Override
    public final List<Vote> provideVotes() throws InvalidVoteException, IllegalStateException {
        ArrayList<Vote> toReturn = new ArrayList<>();
        HashMap<String, Integer> pointScheme = this.getPointScheme();
        Pattern votesPattern = this.compileVotesRegexPattern();
        Pattern startVotingPattern = Pattern.compile(this.getStartVotingPatternString(), Pattern.DOTALL);

        try {
            List<FlickrComment> filteredComments = super.getCommentList()
                    .stream()
                    .filter(c -> !(startVotingPattern.matcher(c.getText()).matches()))
                    .filter(c -> votesPattern.matcher(c.getText()).matches())
                    .collect(Collectors.toList());

            for (FlickrComment comment : filteredComments) {
                Matcher matcher = votesPattern.matcher(comment.getText());
                matcher.find();
                for (int i = 1; i < matcher.groupCount(); i += 2) {
                    if (matcher.start(i) != -1) {
                        // Only happening for groups that are actually matched.
                        Integer pointNum = pointScheme.get(matcher.group(i));
                        if (pointNum == null)
                            pointNum = pointScheme.get(Integer.toString(i % 2));
                        //pointNum = pointNum;

                        Vote vote = new Vote(
                                comment.getAuthor(),
                                matcher.group(i),     // The category
                                matcher.group(i + 1), // The voteable itself
                                pointNum);   // The points
                        toReturn.add(vote);
                    }
                }
            }
        } catch (FlickrException | IllegalStateException ex) {
            throw new IllegalStateException("Non sono riuscito a recuperare i commenti da Flickr, dev'esserci" +
                    "un problema col sito", ex);
        }
        return toReturn;
    }

    /********************************************
     * HELPER METHODS
     ********************************************/

    /**
     * Builds the regex pattern given the categories and all their possible permutations.
     * Odd matching groups contain the voting categories, if they exists.
     * Even matching groups contain the photo ID, in any case.
     *
     * @return The {@link Pattern} matching the categories specified in the JSON file.
     */
    private Pattern compileVotesRegexPattern() {
        List<String> categories = this.getCategoriesName();
        StringBuilder builder = new StringBuilder("(?:.+)?"); //This matches random characters without capturing

        ArrayList<List<String>> permutations = new ArrayList<>();
        HelperMethods.permuteList(categories.toArray(new String[]{}), categories.size(), permutations);
        permutations = (ArrayList<List<String>>) permutations.stream().distinct().collect(Collectors.toList());


        builder.append("(?:");
        for (List<String> permutation : permutations) {
            builder.append("(?:");
            for (String c : permutation) {
                builder.append(String.format("(?:(%s) ?#(\\d{1,}))(?:.+)?", c));
            }
            builder.append(")");
            builder.append("|"); //Alternatives for permutations.
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        builder.append("(?:.+)?"); //This matches random characters without capturing
        return Pattern.compile(builder.toString(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    }


    /*******************************
     * ABSTRACT METHODS
     ********************************/
    abstract List<String> getCategoriesName();

    abstract HashMap<String, Integer> getPointScheme();

    abstract String getStartVotingPatternString();

    abstract String getVoteablePatternString();
}
