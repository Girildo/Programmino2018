package org.altervista.girildo;

public class VoteCategory implements Comparable<VoteCategory> {

    private String name;

    public VoteCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(VoteCategory o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof VoteCategory))
            return false;
        return this.getName().equalsIgnoreCase(((VoteCategory)obj).getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
