package cuatroEnRaya;
import java.util.Random;

public class Juego {

    private Tablero tablero;
    private int turno;

    public Juego() {
        Random rnd = new Random();
        tablero = new Tablero('X', '0');
        int turno = rnd.nextInt(2);>
    }
}
