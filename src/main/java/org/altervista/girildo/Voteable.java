package org.altervista.girildo;


import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Voteable {

    private int id;
    private Agent author;
    private Instant timeOfCreation;

    public Voteable(int id, Agent author, Instant timeOfCreation){
        this.id = id;
        this.author = author;
        this.timeOfCreation = timeOfCreation;
    }


    public Instant getTimeOfCreation() {
        return timeOfCreation;
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
        return String.format("Photo #%d by %s (Posted on %s)",
                this.getId(),
                this.getAuthor().getName(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.getDefault())
                        .withZone(ZoneId.systemDefault())
                        .format(this.getTimeOfCreation()));
    }

}
