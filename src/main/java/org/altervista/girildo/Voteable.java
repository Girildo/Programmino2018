package org.altervista.girildo;

public interface Voteable extends Comparable<Voteable>, Identifiable {
    int addVote();
    int addVotes(int points);

    int removeVote();
    int removeVotes(int points);

    int getVotes();
}
