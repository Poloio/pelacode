package main;

import excepciones.JugadorNoExisteException;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.menuPrincipal();
        } catch (JugadorNoExisteException e) {
            e.printStackTrace();
        }
    }



}
