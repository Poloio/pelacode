package classes;

import cinterfaces.PaidSeries;

import java.time.LocalDate;

public class PaidSeriesImp extends SeriesImp implements PaidSeries {

    private final double price;

    public PaidSeriesImp(String name, LocalDate releaseDate, double rating, int chapters, double price) {
        super(name, releaseDate, rating, chapters);
        this.price = price;
    }

    public PaidSeriesImp(String name, LocalDate releaseDate, int chapters, double price) {
        super(name, releaseDate, chapters);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getRepercussion() {
        double specValue = price * 2;
        return (super.getRating() * 1.5 + specValue) * 4;
    }

    @Override
    public String toString() {
        return "PaidSeriesImp%" +
                "name=" + super.getName() +
                ",releaseDate=" + super.getReleaseDate() +
                ",rating=" + super.getRating() +
                ",chapters=" + super.getChapters() +
                ",price=" + price;
    }
}
