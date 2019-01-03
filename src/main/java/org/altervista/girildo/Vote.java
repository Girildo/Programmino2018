package org.altervista.girildo;

public class Vote {

    private int voted;
    private VoteCategory category;
    private int points;
    private Agent creator;


    public Vote(Agent creator, String categoryName, String voted, int points) throws IllegalArgumentException{
        this(creator, new VoteCategory(categoryName), voted, points);
    }

    public Vote(Agent creator, VoteCategory category, String voted, int points) throws IllegalArgumentException {
        try{
            this.voted = Integer.parseInt(voted);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("%s is not a valid integer key for a photo.", voted), ex);
        }
        this.creator = creator;
        this.category = category;
        this.points = points;
    }



    public int getVoted() {
        return voted;
    }

    public int getPoints() {
        return points;
    }

    public VoteCategory getCategory() {
        return category;
    }

    public Agent getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return String.format("Vote by %s, category: %s, voteable %s, points %d",
                this.getCreator().getName(),
                this.getCategory(),
                this.getVoted(),
                this.getPoints());
    }
}
