package org.altervista.girildo;

import java.util.*;
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

    public void addAllVoteable(Collection<Voteable> voteables){
        for(Voteable voteable:voteables){
            this.addVoteable(voteable);
        }
    }

    public void addAllVotes(Collection<Vote> votes){
        for(Voteable voteable:voteables){
            this.addVoteable(voteable);
        }
    }


    public Table computeTable() {

        final Map<VoteCategory, Integer> emptyTableRow = votes.stream()
                .map(Vote::getCategory)
                .distinct()
                .collect(Collectors.toMap(Function.identity(),  e -> 0));

        this.table = new HashMap<>();

        for (Voteable voteable : this.voteables) {
            table.put(voteable, new HashMap<>(emptyTableRow));
        }

        for (Vote vote : votes) {
            Voteable votedFor = table.keySet()
                    .stream()
                    .filter(voteable -> vote.getVoted() == voteable.getId())
                    .findFirst().orElse(null);
            if(votedFor != null)
                table.get(votedFor).merge(vote.getCategory(), vote.getPoints(), (integer, integer2) -> integer+integer2);
        }

        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Voteable vtbl:table.keySet()) {
            builder.append(vtbl.toString());
            builder.append(": ");
            Map<VoteCategory, Integer> row = table.get(vtbl);
            for (VoteCategory category : row.keySet()) {
                builder.append(category.toString());
                builder.append(": ");
                builder.append(row.get(category));
                builder.append(" | ");
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append("\n");
        }
        return builder.toString();
    }
}
