package org.altervista.girildo;

public interface Voteable extends Comparable<Voteable> {
    int addVote();
    int addVotes(int points);

    int removeVote();
    int removeVotes(int points);

    int getVotes();
    int getId();

    Agent getAuthor();
}
