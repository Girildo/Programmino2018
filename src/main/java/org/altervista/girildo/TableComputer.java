package org.altervista.girildo;

import java.util.List;

public class TableComputer {

    private final VoteableProvider photoProvider;
    private final VoteProvider votesProvider;

    public TableComputer(VoteableProvider photoProvider, VoteProvider votesProvider)
    {
        this.photoProvider = photoProvider;
        this.votesProvider = votesProvider;
    }

    public Table generateTable()
    {
        List<Voteable> photos =  this.photoProvider.provideVoteables();
        List<Vote> votes = this.votesProvider.provideVotes();

        Table table = new Table();

        for(Voteable voteable:photos)
            table.addVoteable(voteable);

        for(Vote vote:votes)
            table.addVote(vote);


        return null;
    }
}
