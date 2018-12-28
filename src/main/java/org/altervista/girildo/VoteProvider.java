package org.altervista.girildo;

import java.util.List;

public interface VoteProvider {
    List<Vote> provideVotes() throws Exception;
}
