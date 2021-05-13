package gestora;

import menu.Menu;

public class Main {
    public static void main(String[] args) {

        Almacen almacen = new Almacen();
        almacen.crearArchivo();
        boolean salir = false;
        do {
            char opcion = Menu.menuPrincipal();

            switch (opcion) {
                case 1:
                    impresionDatos(almacen);
                    break;
                case 2:
                    calculoDatos(almacen);
                    break;
                case 3:
                    gestionDatos(almacen);
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo...");
            }
        } while (!salir);
    }

    private static void impresionDatos(Almacen almacen) {
        boolean salir = false;
        do {
            char opcion = Menu.menuImpresion();

            switch (opcion) {
                case 1:
                    //String fabricante Menu.pedirFabricante();

            }
        } while (!salir);
    }

    private static void calculoDatos(Almacen almacen) {
    }

    private static void gestionDatos(Almacen almacen) {
    }
}
