package org.altervista.girildo.table;

import org.altervista.girildo.Vote;
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

    private Map<Voteable, TableRow> table;

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
        table = new HashMap<>();
        TableRow.TableRowFactory rowFactory = new TableRow.TableRowFactory(votes.stream()
                .map(Vote::getCategory)
                .distinct().collect(Collectors.toList()));

        for (Voteable voteable : this.voteables) {
            table.put(voteable, rowFactory.getEmptyTableRow());
        }

        for (Vote vote : votes) {
            Voteable votedFor = table.keySet()
                    .stream()
                    .filter(voteable -> voteable.getId() == vote.getVoted())
                    .findFirst().orElse(null);

            table.get(votedFor).addPoints(vote.getCategory(), vote.getPoints());
        }
    }

    void sort() {
        LinkedHashMap<Voteable, TableRow> sorted = table.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(this.customComparator()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        this.table = sorted;
    }

    /**
     * Returns a comparator to sort the table first by point and then by time.
     * @return The comparator sorting the table.
     */
    private Comparator<Map.Entry<Voteable, TableRow>> customComparator(){
        return Comparator.comparing(
                (Function<Map.Entry<Voteable, TableRow>, TableRow>) Map.Entry::getValue)
                .thenComparing(o -> o.getKey().getTimeOfCreation(), Comparator.reverseOrder());

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Voteable voteable : table.keySet()) {
            builder.append(voteable.toString());
            builder.append(": ");
            builder.append(table.get(voteable).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
