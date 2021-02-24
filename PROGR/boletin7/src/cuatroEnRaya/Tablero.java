package cuatroEnRaya;

import java.util.Arrays;

public class Tablero {

    private char[][] casillas;
    private char j1;
    private char j2;

    public Tablero(char j1, char j2) {
        casillas = new char[6][7];
        for (char[] fila : casillas) {
            Arrays.fill(fila, ' ');
        }
        this.j1 = j1;
        this.j2 = j2;
    }

    /*
    Entrada: boolean is J1, si la pone el j1 es true
        int columna, entero que indica la columna en la que se pone la ficha empezando por el 1
    Precondiciones: La columna es un número entre 1 y 7
    Postcondiciones: Se pondrá la ficha en el tablero con el carácter del jugador correspondiente.
     */
    public void ponerFicha(boolean isJ1, int columna) {
        //if (columna < 1 || columna > 7) return; ??
        if (columna < 1 || columna > 7) {
            System.out.println("ERROR: La columna indicada para poner ficha no existe");
        } else {
            char ficha;
            if (isJ1) {
                ficha = this.j1;
            } else {
                ficha = this.j2;
            }
            int fila = indiceSiguienteEspacio(columna - 1);
            casillas[fila][columna] = ficha;
        }
    }

    /*
    Este método encuentra el siguiente espacio vacío en una columna dada
     */
    private int indiceSiguienteEspacio(int columna) {
        boolean esLaPrimera = true;
        int fila = -1;

        for (int i = 0; i < casillas.length; i++) {
            if (casillas[columna][i] != ' ') {
                fila = i - 1;
                esLaPrimera = false;
            }
        }

        if (esLaPrimera)
            fila = casillas.length - 1;

        return fila;
    }

    /*
    Devuelve true si no caben mas fichas
     */
    public boolean columnaEstaLlena(int columna) {
        boolean llena = false;
        if (casillas[0][columna] != ' ') llena = true;
        return llena;
    }

    /*
    Imprime el tablero por pantalla
     */
    public void imprimirTablero() {
        for (char[] fila : casillas) {
            System.out.print("|");
            for (char casilla : fila) {
                System.out.print(" ("+casilla+")");
            }
            System.out.print(" |\n");
        }
    }
}
