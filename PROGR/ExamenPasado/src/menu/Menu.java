package menu;
import clases.ApuestaImp;
import enums.Ganador;
import excepciones.JugadorNoExisteException;
import gestora.Sistema;

public class Menu {

    private Sistema gestora;

    public Menu() {
        gestora = new Sistema();
    }

    public void menuPrincipal() throws JugadorNoExisteException {
        boolean error = false; //Registra si ha habido un error para salir del bucle

        System.out.println("Elige una opción" +
                "[1] Realizar Apuesta" +
                "[2] Comprobar apuesta" +
                "[3] Ingresar dinero" +
                "[4] Retirar dinero");

        char opcion = ' ';

        do {
            error = false;

            opcion = Utilidades.pedirCadena().charAt(0);

            if (opcion != '1' && opcion != '2' && opcion != '3' && opcion != '4') {
                System.out.println("Introduce una de las opciones.");
                error = true;
            }
        } while (error);

        int idJugador = pedirIdJugador();

        switch (opcion) {
            case '1':
                realizarApuesta(idJugador);
                break;
            case '2':
                comprobarApuesta(idJugador);
                break;
            case '3':
                ingresarDinero(idJugador);
                break;
            case '4':
                retirarDinero(idJugador);
                break;
        }
    }

    /*
    RESTRICCIONES: El id de jugador debe ser mayor que 0
    POSTCONDICIONES: Se devuelve el número O se lanza la excepcion porque no existe el jugador
    */
    private int pedirIdJugador() throws JugadorNoExisteException {

        System.out.println("Introduce tu ID de jugador");
        int idJugador = Utilidades.pedirEntero();

        if (!gestora.jugadorExiste(idJugador)) {
            throw new JugadorNoExisteException("No existe jugador con la ID indicada");
        }

        return idJugador;
    }

    /*
    PRECONDICIONES: El id existe entre los jugadores
    RESTRICCIONES: La cantidad debe ser mayor que cero
    POSTCONDICIONES: Se añade la apuesta al array de gestora
     */
    private void realizarApuesta(int idJugador) {

        System.out.println("Introduce cantidad");
        double cantidad = Utilidades.pedirEntero();

        System.out.println("Introduce a qué apuestas" +
                "[1] Gana Local" +
                "[2] Gana Visitante" +
                "[X] Empate");

        Ganador ganadorApostado = null;

        //Menu seleccion de ganador
        boolean error = false;
        do {
            error = false;
            char opcion = Utilidades.pedirCadena().toUpperCase().charAt(0);

            switch (opcion) {
                case '1':
                    ganadorApostado = Ganador.LOCAL;
                    break;
                case '2':
                    ganadorApostado = Ganador.VISITANTE;
                    break;
                case 'X':
                    ganadorApostado = Ganador.EMPATE;
                    break;
                default:
                    System.out.println("Introduce una de las opciones");
                    error = true;
            }
        } while (error);

        ApuestaImp apuesta = new ApuestaImp(ganadorApostado,idJugador,cantidad);

        gestora.addApuesta(apuesta);

    }

    /*
    PRECONDICIONES: ID de jugador existe en gestora
    POSTCONDICIONES: se actualiza el saldo según indica el resultado de la apuesta elegida
     */
    private void comprobarApuesta(int idJugador) {

        ApuestaImp[] apuestas = gestora.getApuestasDeJugador(idJugador);

        //imprimirApuestasPorComprobar();

        //Elegir apuesta en un menu

        gestora.comprobarResultado(apuestas[69]); //TODO usar indice del array obtenido mediante el menú


    }

    private void ingresarDinero(int idJugador) {
        System.out.println("En construccion");
    }

    private void retirarDinero(int idJugador) {
        System.out.println("En construccion");
    }

}
