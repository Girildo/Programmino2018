package org.altervista.girildo;

import org.jetbrains.annotations.NotNull;

//TODO Get rid of this stupid interface.
public class Voteable {


    private int id;
    private Agent author;

    public Voteable(int id, Agent author){
        this.id = id;
        this.author = author;
    }

    public int getId() {
        return id;
    }


    public Agent getAuthor() {
        return this.author;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Voteable))
            return false;
        return this.getId() == ((Voteable) o).getId();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return String.format("Photo #%d by %s",
                this.getId(),
                this.getAuthor().getName());
    }
}
