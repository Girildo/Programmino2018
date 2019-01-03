package org.altervista.girildo;

/**
 * Represents an agent able to own photos and cast votes.
 */
public abstract class  Agent {

    private final String name;
    private final String id;

    public final String getName() {
        return name;
    }

    public final String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Agent))
            return false;
        return this.getId().equals(((Agent) o).getName());
    }

    @Override
    public int hashCode(){
        return this.getId().hashCode();
    }

    public Agent(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
}
