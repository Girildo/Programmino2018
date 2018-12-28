package org.altervista.girildo;

import java.util.List;

public interface VoteableProvider {
    List<Voteable> provideVoteables() throws Exception;
}
