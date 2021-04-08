package pizzeria;

public class DishImp implements Dish{

    private Flavour flavour;
    private boolean served;

    public DishImp(Flavour flavour) {
        this.flavour = flavour;
        this.served = false;
    }

    @Override
    public Flavour getFlavour() {
        return flavour;
    }

    @Override
    public boolean isServed() {
        return served;
    }

    public void markAsServed() {
        served = true;
    }
}
