package org.altervista.girildo.flickr;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.groups.discuss.GroupDiscussInterface;
import com.flickr4java.flickr.groups.discuss.Reply;
import com.flickr4java.flickr.groups.discuss.ReplyObject;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.File;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to interface with Flickr and get
 */
class FlickrInterface {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String KEY_FILE_NAME = "/FlickrKey.json";
    private static final String DISCUSSION_ID_REGEX =
            "(?:(?:https?://)?www\\.flickr\\.com/groups/)(.+)(?:/discuss/)(\\d+)/?";

    private static final Pattern DISCUSSION_ID_PATTERN = Pattern.compile(DISCUSSION_ID_REGEX);
    private static FlickrInterface singleton;
    private Flickr flickr;

    private FlickrInterface() {
        File keyFile = new File(FlickrInterface.class.getResource(KEY_FILE_NAME).getFile());
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> s = JSON_FACTORY.fromReader(new FileReader(keyFile),
                    HashMap.class);
            flickr = new Flickr(s.get("key"), s.get("secret"), new REST());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getTopicIDFromUrl(String url) {
        url = url.trim();
        Matcher matcher = DISCUSSION_ID_PATTERN.matcher(url);
        matcher.find();
        return matcher.group(2);
    }

    static FlickrInterface getInstance() {
        if (singleton == null)
            singleton = new FlickrInterface();
        return singleton;
    }

    List<FlickrComment> getCommentsFromDiscussion(String url) throws IllegalStateException, FlickrException {
        GroupDiscussInterface dInterface = flickr.getDiscussionInterface();
        ArrayList<Reply> repList = null;
        try {
            String topicID = getTopicIDFromUrl(url);
            int count = dInterface.getTopicInfo(topicID).getCountReplies(); //count delle risposte
            ReplyObject rep = dInterface.getReplyList(topicID, count, 1); //ottiene l'oggetto dall'API
            repList = rep.getReplyList(); //estrae la lista delle risposte
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("Il link alla discussione Flickr che hai inserito non è valido. " +
                    "Assicurati di averlo copiato interamente!", ex);
        } catch (FlickrException ex) {
            throw new FlickrException("Sembra esserci un errore con Flickr", ex);
        }

        if (repList == null)
            throw new FlickrException("RepList null (Errore di Flickr!)");

        return repList.stream()
                .map(reply ->
                        new FlickrComment(reply.getMessage(),
                                new FlickrUser(reply.getAuthorname(), reply.getAuthorId()),
                                Instant.ofEpochSecond(Long.parseLong(reply.getDatecreate()))))
                .collect(Collectors.toList());
    }
}