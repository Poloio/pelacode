package menu;

import clases.AlumnoImp;
import clases.ExamenImp;
import clases.IntentoImp;
import enums.Asignatura;
import main.Gestora;

import java.util.Calendar;
import java.util.Date;
public class Menu {

    public static final String MENU_PRINCIPAL =
            "Bienvenido al gestor de calificaciones, indica una opción\n" +
            "[1] Dar de alta alumno\n" +
            "[2] Añadir nuevo examen\n" +
            "[3] Asignar calificacion\n" +
            "[4] Mostrar estadísticas de alumno\n" +
            "[5] Mostrar estadísticas de examen" +
            "[6] Salir"
    ;

    /*
    PRECONDICIONES: ninguna
    POSTCONDICIONES: se devuelve la elección del usuario
    SALIDAS: caracter opcion, la elegida por el usuario
     */
    public static char menuPrincipal() {
        System.out.println(MENU_PRINCIPAL);
        return Utilidades.pedirCadena().charAt(0);
    }

    public static int pedirIDAlumno(Gestora gestora) throws NoExisteAlumnoOExamenException {
        System.out.println("Indica el ID del alumno");
        int idAlumno = Utilidades.pedirEntero();
        if (!gestora.existeAlumnoConId(idAlumno)) {
            throw new NoExisteAlumnoOExamenException("No existe el alumno con el id indicado");
        }
        return idAlumno;
    }

    //Pide los datos del alumno a añair y pasa los datos a la gestora para construirlo
    public static AlumnoImp pedirAlumno() {

        System.out.println("Introduce el nombre y los apellidos del alumno");
        String nombreApellidos = Utilidades.pedirCadena();

        Date fecha = pedirFecha();

        return new AlumnoImp(fecha, nombreApellidos);
    }

    public static Date pedirFecha() {
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
    public static ExamenImp pedirExamen() {
        Asignatura asignatura = pedirAsignatura();
        Date fecha = pedirFecha();
        boolean simulacro = preguntarSimulacro();

        return new ExamenImp(asignatura, fecha, simulacro);
    }

    private static boolean preguntarSimulacro() {
        boolean isSimulacro = false;
        boolean error = false;
        do {
            if (error) {
                System.out.println("Introduce una de las opciones");
            }
            System.out.println("Es un simulacro de examen? S/N");
            char opcion = Utilidades.pedirCadena().toUpperCase().charAt(0);

            switch (opcion) {
                case 'S' -> isSimulacro = true;
                case 'N' -> isSimulacro = false;
                default -> error = true;
            }
        } while (error);

        return isSimulacro;
    }

    private static Asignatura pedirAsignatura() {
        Asignatura asignatura = null;
        boolean error = false;
        do {
            if (error) {
                System.out.println("Introduce una de las opciones");
            }
            System.out.println("Selecciona la asignatura del examen:\n" +
                    "[1] Programación\n" +
                    "[2] FOL\n" +
                    "[3] Lenguajes de marcas\n");

            char opcion = Utilidades.pedirCadena().charAt(0);

            switch (opcion) {
                case '1' -> asignatura = Asignatura.PROG;
                case '2' -> asignatura = Asignatura.FOL;
                case '3' -> asignatura = Asignatura.LM;
                default -> error = true;
            }
        } while (error);

        return asignatura;
    }

    //Pide los datos del intento y los pasa a la gestora
    public static IntentoImp pedirIntento(Gestora gestora) throws NoExisteAlumnoOExamenException {

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

        return new IntentoImp(idAlumno, idExamen, calificacion);
    }

    private void estadisticasExamen() {
        System.out.println("En construcción.");
    }

}
