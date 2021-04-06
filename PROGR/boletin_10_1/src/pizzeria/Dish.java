package pizzeria;

public interface Dish {

    Flavour getFlavour();
    void markAsServed();
    boolean isServed();
}
