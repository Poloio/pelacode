package menu;

import clases.AlumnoImp;
import main.Gestora;

import java.util.Calendar;
import java.util.Date;

public class Menu {

    private Gestora gestora;

    public Menu() {
        this.gestora = new Gestora();
    }

    /*
    PRECONDICIONES: ninguna
    POSTCONDICIOENS: el programa seguirá por el camino que elija el usuario
     */
    public void menuPrincipal() {

        boolean salir = false;
        do {
            System.out.println("Bienvenido al gestor de calificaciones, indica una opción\n" +
                    "[1] Dar de alta alumno\n" +
                    "[2] Añadir nuevo examen\n" +
                    "[3] Asignar calificacion\n" +
                    "[4] Mostrar estadísticas de alumno\n" +
                    "[5] Mostrar estadísticas de examen" +
                    "[6] Salir");

            char opcion = Utilidades.pedirCadena().charAt(0);

            try {
                switch (opcion) {
                    case '1': pedirAlumno(); break;
                    case '2': pedirExamen(); break;
                    case '3': pedirIntento(); break;
                    case '4': estadisticasAlumno(); break;
                    case '5': estadisticasExamen(); break;
                    case '6':
                        salir = true;
                        System.out.println("Saliendo . . .");
                        break;
                    default: System.out.println("Introduce una de las opciones");
                }

            } catch (NoExisteAlumnoOExamenException e) {
                e.printStackTrace();
            }
        } while (!salir);
    }

    //Pide los datos del alumno a añair y pasa los datos a la gestora para construirlo
    private void pedirAlumno() {

        System.out.println("Introduce el nombre y los apellidos del alumno");
        String nombreApellidos = Utilidades.pedirCadena();

        Date fecha = pedirFecha();

        //TODO pasar al metodo addAlumno en gestora con estos datos

    }

    private Date pedirFecha() {
        boolean error = false;
        Date fecha = null;

        do {

            System.out.println("Introduce el año de nacimiento");
            int anio = Utilidades.pedirEntero();
            System.out.println("Introduce el mes de nacimiento (número)");
            int mes = Utilidades.pedirEntero();
            System.out.println("Introduce el día de nacimiento");
            int dia = Utilidades.pedirEntero();
            //TODO validar que el día puede existir segun bisiesto y restringir años de entrada

            switch (mes) {
                case 1 -> fecha = new Date(anio, Calendar.JANUARY, dia);
                case 2 -> fecha = new Date(anio, Calendar.FEBRUARY, dia);
                case 3 -> fecha = new Date(anio, Calendar.MARCH, dia);
                case 4 -> fecha = new Date(anio, Calendar.APRIL, dia);
                case 5 -> fecha = new Date(anio, Calendar.MAY, dia);
                case 6 -> fecha = new Date(anio, Calendar.JUNE, dia);
                case 7 -> fecha = new Date(anio, Calendar.JULY, dia);
                case 8 -> fecha = new Date(anio, Calendar.AUGUST, dia);
                case 9 -> fecha = new Date(anio, Calendar.SEPTEMBER, dia);
                case 10 -> fecha = new Date(anio, Calendar.OCTOBER, dia);
                case 11 -> fecha = new Date(anio, Calendar.NOVEMBER, dia);
                case 12 -> fecha = new Date(anio, Calendar.DECEMBER, dia);
                default -> error = true;
            }

            if (dia < 1 || dia > 31) {
                error = true;
            }

            if (error) {
                System.out.println("Fecha no válida");
            }

        } while (error);

        return fecha;
    }

    //Pide los datos del examen y los pasa a la gestora
    private void pedirExamen() {
        System.out.println("En construccion");
        //TODO terminar método para recoger datos y pasarlos
    }

    //Pide los datos del intento y los pasa a la gestora
    private void pedirIntento() throws NoExisteAlumnoOExamenException {

        System.out.println("Indica el ID del examen");
        int idExamen = Utilidades.pedirEntero();

        if (!gestora.existeExamenConId(idExamen)) {
            throw new NoExisteAlumnoOExamenException("No existe el examen con el ID indicado");
        }


        System.out.println("Indica el ID del alumno");
        int idAlumno = Utilidades.pedirEntero();

        if (!gestora.existeAlumnoConId(idAlumno)) {
            throw new NoExisteAlumnoOExamenException("No existe el alumno con el ID indicado");
        }

        System.out.println("Indica la calificación obtenida (0-10)");
        int calificacion = Utilidades.pedirEnteroConRango(0,10);

        gestora.addIntento(idAlumno, idExamen, calificacion);
    }

    private void estadisticasAlumno() throws NoExisteAlumnoOExamenException {
        System.out.println("Indica el ID del alumno");
        int idAlumno = Utilidades.pedirEntero();

        if (!gestora.existeAlumnoConId(idAlumno)) {
            throw new NoExisteAlumnoOExamenException("No existe el alumno con el id indicado");
        }

        System.out.println(gestora.getEstadisticasAlumno(idAlumno));
    }

    private void estadisticasExamen() {
        System.out.println("En construcción.");
    }

}
