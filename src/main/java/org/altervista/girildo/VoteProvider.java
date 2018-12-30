package org.altervista.girildo;

import org.altervista.girildo.exceptions.InvalidVoteException;

import java.util.List;

public interface VoteProvider {
    List<Vote> provideVotes() throws InvalidVoteException, IllegalStateException;
}
