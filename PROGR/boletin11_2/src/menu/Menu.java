package menu;

import java.util.Locale;

public class Menu {

    public static char menuPrincipal() {
        System.out.println("""
                Elige una de las opciones:\n\n
                [1] Funciones de impresión de datos.\n
                [2] Funciones de cálculo.\n
                [3] Funciones de gestión.""");

        return Utilidades.pedirCadena().charAt(0);
    }

    public static char menuImpresion() {
        System.out.println("""
                Elige una de las opciones:\n
                [1] Lista de productos de un fabricante.\n
                [2] Productos que no han caducado en una fecha.\n
                [3] Valor total de todos los productos.""");

        return Utilidades.pedirCadena().charAt(0);
    }
}
