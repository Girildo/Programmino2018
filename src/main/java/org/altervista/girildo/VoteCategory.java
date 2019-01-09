package org.altervista.girildo;

public class VoteCategory implements Comparable<VoteCategory> {

    final private String internalName;
    final private String name;

    public static final VoteCategory SPECIAL_TOTAL_CATEGORY = new TotalCategory();


    public VoteCategory(String name) {
        this(name, name);
    }

    private VoteCategory(String internalName, String name){
        this.name = name;
        this.internalName = internalName;
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
    public boolean equals(Object other) {
        if(!(other instanceof VoteCategory))
            return false;
        return this.internalName.equalsIgnoreCase(((VoteCategory) other).internalName);
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }


    private static class TotalCategory extends VoteCategory {
        TotalCategory() {
            super("@@@Total@@@", "Total");
        }
    }
}
