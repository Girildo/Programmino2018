package org.altervista.girildo;

public class Photo implements Voteable {

    private int votes;
    private int id;
    private Agent author;

    public Photo(int id, Agent author){
        this.id = id;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int addVote() {
        this.addVotes(1);
        return this.getVotes();
    }

    public int addVotes(int points) {
        this.votes++;
        return this.getVotes();
    }

    public int removeVote() {
        this.removeVotes(1);
        return this.getVotes();
    }

    public int removeVotes(int points) {
        this.votes -= points;
        return this.getVotes();
    }

    public int getVotes() {
        return this.votes;
    }

    @Override
    public Agent getAuthor() {
        return this.author;
    }

    public int compareTo(Voteable o) {
        return(Integer.compare(this.getVotes(), o.getVotes()));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Photo))
            return false;
        return this.getId() == ((Photo) o).getId();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return String.format("Photo #%d by %s with %d total votes",
                this.getId(),
                this.getAuthor().getName(),
                this.getVotes());
    }
}
