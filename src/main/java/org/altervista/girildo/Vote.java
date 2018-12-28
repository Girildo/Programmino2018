package org.altervista.girildo;

public class Vote {

    private String voted;
    private VoteCategory category;
    private int points;

//    public Vote(String voted, String categoryName, int points){
//        this(voted, new VoteCategory(categoryName), points);
//    }

    public Vote(String voted, VoteCategory category, int points)
    {
        this.voted = voted;
        this.category = category;

        this.points = points;
    }

    public String getVoted() {
        return voted;
    }

    public int getPoints() {
        return points;
    }

    public VoteCategory getCategory() {
        return category;
    }


}
