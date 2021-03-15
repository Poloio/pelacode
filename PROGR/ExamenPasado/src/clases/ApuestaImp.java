package clases;
import enums.Ganador;
import interfaces.Apuesta;

public class ApuestaImp implements Apuesta {

    private Ganador ganadorApostado;
    private int IDJugador;
    private double cantidad;

    public ApuestaImp(Ganador ganadorApostado, int IDJugador, double cantidad) {
        this.ganadorApostado = ganadorApostado;
        this.IDJugador = IDJugador;
        this.cantidad = cantidad;
    }

    @Override
    public Ganador getGanadorApostado() {
        return ganadorApostado;
    }

    @Override
    public void setGanadorApostado(Ganador ganadorApostado) {
        this.ganadorApostado = ganadorApostado;
    }

    @Override
    public int getIDJugador() {
        return IDJugador;
    }

    @Override
    public double getCantidad() {
        return cantidad;
    }

    @Override
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
