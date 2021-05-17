package cinterfaces;

import java.time.LocalDate;

public interface Series extends Comparable<Series> {

    String getName();
    LocalDate getReleaseDate();

    double getRating();
    void setRating(double rating);

    int getChapters();

    double getRepercussion();

}
