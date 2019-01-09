package org.altervista.girildo.table;

import org.altervista.girildo.Vote;
import org.altervista.girildo.VoteProvider;
import org.altervista.girildo.Voteable;
import org.altervista.girildo.VoteableProvider;

import java.util.List;


/**
 * Computes a table collecting {@link Vote}s and {@link Voteable}s from a {@link VoteProvider} and a
 * {@link VoteableProvider}, respectively.
 */
public class TableComputer {

    private final VoteableProvider photoProvider;
    private final VoteProvider votesProvider;

    public TableComputer(VoteableProvider photoProvider, VoteProvider votesProvider)
    {
        this.photoProvider = photoProvider;
        this.votesProvider = votesProvider;
    }

    public Table generateTable() throws Exception {
        List<Voteable> photos =  this.photoProvider.provideVoteables();
        List<Vote> votes = this.votesProvider.provideVotes();

        Table table = new Table();

        for(Voteable voteable:photos)
            table.addVoteable(voteable);


        for(Vote vote:votes)
            table.addVote(vote);

        table.fill();
        return table;
    }
}
