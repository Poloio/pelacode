package pizzeria;

public class PizzaImp extends DishImp implements Pizza{

    private static int orderedPizzas;
    private static int servedPizzas;

    private PizzaSize pizzaSize;

    public PizzaImp(Flavour flavour, PizzaSize pizzaSize) {
        super(flavour);
        this.pizzaSize = pizzaSize;
        orderedPizzas++;
    }

    @Override
    public PizzaSize getSize() {
        return pizzaSize;
    }

    public static int getOrderedPizzas() {
        return orderedPizzas;
    }

    public static int getServedPizzas() {
        return servedPizzas;
    }

    @Override
    public void markAsServed() {
        super.markAsServed();
        servedPizzas++;
    }
}
