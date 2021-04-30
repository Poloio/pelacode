package main;

import clases.AlumnoImp;
import clases.ExamenImp;
import clases.IntentoImp;
import enums.Asignatura;
import menu.Menu;
import menu.NoExisteAlumnoOExamenException;

import java.util.Date;

public class Main {

    private static final Gestora GESTORA = new Gestora();

    public static void main(String[] args) {

        crearPruebas();

        boolean salir = false;

        do {
            char opcion = Menu.menuPrincipal();

            switch (opcion) {
                case '1' -> nuevoAlumno();
                case '2' -> nuevoExamen();
                case '3' -> nuevoIntento();
                case '4' -> estadisticasAlumno();
                case '5' -> estadisticasExamen();
                case '6' -> salir = true;
                default -> System.out.println("Introduce una de las opciones");
            }
        } while (!salir);
    }

    private static void nuevoAlumno() {
        try {
            AlumnoImp alu = Menu.pedirAlumno();
            GESTORA.add(alu);
        } catch(MaxAlumnosException e) {
            e.printStackTrace();
        }
    }

    private static void nuevoExamen() {
        ExamenImp exam = Menu.pedirExamen();
        GESTORA.add(exam);
    }

    private static void nuevoIntento() {
        try {
            IntentoImp intento = Menu.pedirIntento(GESTORA);
            GESTORA.add(intento);
        } catch (NoExisteAlumnoOExamenException e) {
            e.printStackTrace();
        }
    }

    private static void estadisticasAlumno() {
        try {
            int idAlumno = Menu.pedirIDAlumno(GESTORA);
            System.out.println(GESTORA.getEstadisticasAlumno(idAlumno));
        } catch (NoExisteAlumnoOExamenException e) {
            e.printStackTrace();
        }
    }

    private static void estadisticasExamen() {
        System.out.println("En construcción.");
    }

    private static void crearPruebas() {
        try {
            GESTORA.add(new AlumnoImp(new Date(), "Paco Toronjo"));
            GESTORA.add(new ExamenImp(Asignatura.FOL, new Date(), false));
        } catch (MaxAlumnosException e) {
            e.printStackTrace();
        }
    }

}
