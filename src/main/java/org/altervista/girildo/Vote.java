package org.altervista.girildo;

public class Vote {

    private Voteable voted;
    private VoteCategory category;
    private int points;

    public Vote(Voteable voted, String categoryName, int points){
        this(voted, new VoteCategory(categoryName), points);
    }

    public Vote(Voteable voted, VoteCategory category, int points)
    {
        this.voted = voted;
        this.category = category;

        this.points = points;
    }

    public Voteable getVoted() {
        return voted;
    }

    public int getPoints() {
        return points;
    }

    public VoteCategory getCategory() {
        return category;
    }


}
