package interfaces;
import enums.Ganador;

public interface Apuesta {

    Ganador getGanadorApostado();
    void setGanadorApostado(Ganador ganadorApostado);

    int getIDJugador();

    double getCantidad();
    void setCantidad(double Cantidad);
}
