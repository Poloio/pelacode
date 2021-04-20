package pizzeria;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<PaniniImp> paninis = new ArrayList<>();
        List<PizzaImp> pizzas = new ArrayList<>();

        paninis.add(new PaniniImp(PaniniSize.ALMERIA, Flavour.FUNGHI));
        paninis.add(new PaniniImp(PaniniSize.GRANADA, Flavour.CUATRO_QUESOS));
        paninis.add(new PaniniImp(PaniniSize.SEVILLA, Flavour.MARGARITA));
        paninis.get(0).markAsServed();

        System.out.println(" Paninis");
        for (PaniniImp panini : paninis) {
            System.out.println(panini.getFlavour() + " " + panini.getSize() + " served : " + panini.isServed());
        }
        //Habría estado más bonito el toString en cada clase

        pizzas.add(new PizzaImp(Flavour.FUNGHI, PizzaSize.MEDIANA));
        pizzas.add(new PizzaImp(Flavour.CUATRO_QUESOS, PizzaSize.MEDIANA));
        pizzas.add(new PizzaImp(Flavour.MARGARITA, PizzaSize.FAMILIAR));
        pizzas.get(2).markAsServed();

        System.out.println(" Pizzas");
        for (PizzaImp pizza : pizzas) {
            System.out.println(pizza.getFlavour() + " " + pizza.getSize() + " served: " + pizza.isServed());
        }

    }
}
