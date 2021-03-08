package cuatroEnRaya;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private Tablero tablero;
    private int turno;

    public Juego() {
        Random rnd = new Random();
        tablero = new Tablero('X', '0');
        int turno = rnd.nextInt(2);
    }

    public void realizarTurno() {

        boolean turnoDeJ1 = false;
        while (true) {
            switch (turno) {
                case 0:
                    System.out.println("Es el turno de J1");
                    turnoDeJ1 = true;
                    break;
                case 1:
                    System.out.println("Es el turno de J2");
                    break;
            }

            Scanner sc = new Scanner(System.in);

            tablero.imprimirTablero();
            int columna = -1;
            boolean estaLlena = false;

            do {
                System.out.println("Elige una columna del 1 al 7 para poner la ficha");
                columna = sc.nextInt() - 1;
                estaLlena = tablero.columnaEstaLlena(columna);
                if (estaLlena)
                    System.out.println("La columna ya está llena");
            } while (estaLlena);

            tablero.ponerFicha(turnoDeJ1, columna);

            tablero.imprimirTablero();
        }
    }


}
