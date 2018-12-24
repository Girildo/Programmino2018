package org.altervista.girildo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Table {

    private List<Voteable> voteables;
    private List<Vote> votes;

    private HashMap<Voteable, Map<VoteCategory, Integer>> table;

    public Table() {
        this.voteables = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public void addVoteable(Voteable voteable) {
        this.voteables.add(voteable);
    }

    public void addVote(Vote vote) {
        this.votes.add(vote);
    }


    public Table computeTable() {

        final Map<VoteCategory, Integer> emptyTableRow = votes.stream()
                .map(Vote::getCategory)
                .distinct()
                .collect(Collectors.toMap(Function.identity(),  e -> 0));

        this.table = new HashMap<>();

        for (Voteable voteable : this.voteables) {
            table.put(voteable, emptyTableRow);
        }

        for (Vote vote : votes) {
            table.get(vote.getVoted()).put(vote.getCategory(), vote.getPoints());
        }

        return this;
    }

}
