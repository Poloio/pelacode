package classes;

import cinterfaces.Series;

import java.time.LocalDate;

public class SeriesImp implements Series {

    private final String name;
    private final LocalDate releaseDate;
    private double rating = 5; //5 by default
    private final int chapters;

    public SeriesImp(String name, LocalDate releaseDate, double rating, int chapters) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.chapters = chapters;
    }

    public SeriesImp(String name, LocalDate releaseDate, int chapters) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.chapters = chapters;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int getChapters() {
        return chapters;
    }

    @Override
    public double getRepercussion() {
        double specValue = chapters * 3;
        return (rating * 1.5 + specValue) * 4;
    }


    @Override
    public int compareTo(Series o) {
        return o.getName().compareTo(name);
    }

    @Override
    public String toString() {
        return "SeriesImp%" +
                "name=" + name +
                ",releaseDate=" + releaseDate +
                ",rating=" + rating +
                ",chapters=" + chapters;
    }
}
