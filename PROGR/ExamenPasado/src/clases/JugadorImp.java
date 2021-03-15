package clases;
import enums.Ganador;
import interfaces.Jugador;

public class JugadorImp implements Jugador {

    private double saldo;
    private String alias;
    private int IDJugador;
    private int apuestasRealizadas;
    private int apuestasGanadas;

    public static int nJugadores;

    public JugadorImp(double saldo, String alias) {
        this.saldo = saldo;
        this.alias = alias;
        nJugadores++; // Usamos el número actual de jugadores para asignar el ID
        this.IDJugador = nJugadores;
        apuestasGanadas = 0;
        apuestasRealizadas = 0;
    }

    @Override
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public void addSaldo(double saldo) {
        this.saldo += saldo;
    }

    @Override
    public void removeSaldo(double saldo) {
        this.saldo -= saldo;
    }

    @Override
    public Ganador getGanadorApostado() {
        return null;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public int getIDJugador() {
        return IDJugador;
    }
}
