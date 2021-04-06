package pizzeria;

public class PaniniImp extends DishImp implements Panini{

    private static int orderedPaninis;
    private static int servedPaninis;

    private PaniniSize size;

    public PaniniImp(PaniniSize size, Flavour flavour) {
        super(flavour);
        this.size = size;
    }

    @Override
    public PaniniSize getSize() {
        return size;
    }

    public static int getOrderedPaninis() {
        return orderedPaninis;
    }

    public static int getServedPaninis() {
        return servedPaninis;
    }

    @Override
    public void markAsServed() {
        super.markAsServed();
        servedPaninis++;
    }
}
