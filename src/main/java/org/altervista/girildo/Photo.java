package org.altervista.girildo;

class Photo implements Voteable {

    private int votes;
    private int id;

    public Photo(int id){
        this.id = id;
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

    public int compareTo(Voteable o) {
        return(Integer.compare(this.getVotes(), o.getVotes()));
    }
}
