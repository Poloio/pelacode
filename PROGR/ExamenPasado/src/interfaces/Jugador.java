package interfaces;

import enums.Ganador;

public interface Jugador {

    Ganador getGanadorApostado();
    double getSaldo();
    void setSaldo(double saldo);
    void addSaldo(double saldo);
    void removeSaldo(double saldo);

    String getAlias();

}
