package org.altervista.girildo.table;

import org.altervista.girildo.Vote;
import org.altervista.girildo.VoteCategory;
import org.altervista.girildo.Voteable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents the table of photos. Keeps track of votes in different categories.
 * Access using {@link TableComputer}.
 */
public class Table {

    private List<Voteable> voteables;
    private List<Vote> votes;

    private TreeMap<Voteable, Map<VoteCategory, Integer>> table;

    Table() {
        this.voteables = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    void addVoteable(Voteable voteable) {
        this.voteables.add(voteable);
    }

    void addVote(Vote vote) {
        this.votes.add(vote);
    }

    public void addAllVoteable(Collection<Voteable> voteables) {
        for (Voteable voteable : voteables) {
            this.addVoteable(voteable);
        }
    }

    public void addAllVotes(Collection<Vote> votes) {
        for (Voteable voteable : voteables) {
            this.addVoteable(voteable);
        }
    }


    void fill() {
        final Map<VoteCategory, Integer> emptyTableRow = votes.stream()
                .map(Vote::getCategory)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), e -> 0));

        emptyTableRow.put(VoteCategory.SPECIAL_TOTAL_CATEGORY, 0);



        for (Voteable voteable : this.voteables) {
            table.put(voteable, new HashMap<>(emptyTableRow));
        }

        for (Vote vote : votes) {

            Voteable votedFor = table.keySet()
                    .stream()
                    .filter(voteable -> voteable.getId() == vote.getVoted())
                    .findFirst().orElse(null);

            if (Objects.nonNull(votedFor)) {
                table.get(votedFor).merge(vote.getCategory(), vote.getPoints(),
                        (oldPoints, delta) -> oldPoints + delta);

                table.get(votedFor).merge(VoteCategory.SPECIAL_TOTAL_CATEGORY, vote.getPoints(),
                        (oldPoints, delta) -> oldPoints + delta);
            }
        }
    }


        @Override
        public String toString () {
            StringBuilder builder = new StringBuilder();
            for (Voteable voteable : table.keySet()) {
                builder.append(voteable.toString());
                builder.append(": ");
                Map<VoteCategory, Integer> row = table.get(voteable);
                for (VoteCategory category : row.keySet()) {
                    if(category.equals(VoteCategory.SPECIAL_TOTAL_CATEGORY))
                        continue;
                    builder.append(category.toString());
                    builder.append(": ");
                    builder.append(row.get(category));
                    builder.append(" | ");
                }

                VoteCategory category = VoteCategory.SPECIAL_TOTAL_CATEGORY;
                builder.append(category.toString());
                builder.append(": ");
                builder.append(row.get(category));

                builder.append("\n");
            }
            return builder.toString();
        }
    }
