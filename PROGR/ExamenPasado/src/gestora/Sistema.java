package gestora;
import clases.JugadorImp;
import clases.ApuestaImp;
import enums.Ganador;

import java.util.Arrays;
import java.util.Random;

public class Sistema {

    JugadorImp[] jugadores;
    ApuestaImp[] apuestas;

    public Sistema() {
        jugadores = new JugadorImp[10];
        apuestas = new ApuestaImp[10];
    }

    public boolean jugadorExiste(int id) {
        boolean existe = false;

        for (JugadorImp jugador : jugadores) {
            if (jugador.getIDJugador() == id) {
                existe = true;
                break;// El ID me lo ha recomendado, si es muy feo ponerlo no me extraña pero no me quites puntos ;)
            }
        }

        return existe;
    }

    public void addApuesta(ApuestaImp apuesta) {
        boolean lleno = true;

        for (ApuestaImp apuestaActual : apuestas) {
            if (apuestaActual == null) {
                apuestaActual = apuesta;
                lleno = false;
                break;
            }
        }

        if (lleno) {
            ApuestaImp[] apuestasNuevas = Arrays.copyOf(apuestas, apuestas.length + 10);
            addApuesta(apuesta);
        }
    }

    public ApuestaImp[] getApuestasDeJugador(int id) {

        int coincidencias = contarCoincidencias(id);

        ApuestaImp[] apuestasDeJugador = new ApuestaImp[coincidencias];

        int i = 0;
        for (ApuestaImp apuesta : apuestas) {
            if (apuesta.getIDJugador() == id) {
                apuestasDeJugador[i] = apuesta;
                i++;
            }
        }

        return apuestasDeJugador;
    }

    private int contarCoincidencias(int id) {
        int coincidencias = 0;

        for (ApuestaImp apuestaImp : apuestas) {
            if (apuestaImp.getIDJugador() == id) {
                coincidencias++;
            }
        }

        return coincidencias;
    }

    /*
    PRECONDICIONES: ninguna
    POSTCONDICIONES: el saldo del jugador se ve afectado según el resultado
     */
    public void comprobarResultado(ApuestaImp apuesta) {
        Random rnd = new Random();
        int local = rnd.nextInt(4);
        int visitante = rnd.nextInt(4);
        Ganador resultado;

        if (local > visitante) {
            resultado = Ganador.LOCAL;
        } else if (visitante > local) {
            resultado = Ganador.VISITANTE;
        } else {
            resultado = Ganador.EMPATE;
        }

        JugadorImp jugador = getJugadorPorId(apuesta.getIDJugador());

        if (apuesta.getGanadorApostado() == resultado) {
            jugador.addSaldo(4*apuesta.getCantidad());
            //jugador.addApuestasGanadas();
        } else {
            jugador.removeSaldo(apuesta.getCantidad());
        }
    }

    private JugadorImp getJugadorPorId(int id) {
        JugadorImp jugadorEncontrado = null;

        for (JugadorImp jugadorAct : jugadores) {
            if (jugadorAct.getIDJugador() == id) {
                jugadorEncontrado = jugadorAct;
                break;
            }
        }

        return jugadorEncontrado;
    }
}
