package main;


import clases.*;
import enums.Asignatura;

import javax.print.attribute.standard.MediaPrintableArea;
import java.util.Arrays;

public class Gestora {

    AlumnoImp[] alumnos;
    ExamenImp[] examenes;
    IntentoImp[] intentos;

    public Gestora() {
        alumnos = new AlumnoImp[33];//El máximo de alumnos es 33
        examenes = new ExamenImp[10];
        intentos = new IntentoImp[10];

    }

    public boolean existeExamenConId(int idExamen) {
        boolean existe = false;

        for (ExamenImp examen : examenes) {
            if (examen.getId() == idExamen) {
                existe = true;
                break;// Lo veo feillo pero el IDE lo recomienda, ya me dices que te parece
            }
        }
        return existe;
    }

    public boolean existeAlumnoConId(int idAlumno) {
        boolean existe = false;

        for (AlumnoImp alumno : alumnos) {
            if (alumno.getId() == idAlumno) {
                existe = true;
                break;// Lo veo feillo pero el IDE lo recomienda, ya me dices que te parece
            }
        }
        return existe;
    }

    public void addIntento(int idAlumno, int idExamen, int calificacion) {

        IntentoImp newIntento = new IntentoImp(idAlumno,idExamen,calificacion);

        boolean lleno = true;
        int index = 0;
        for (IntentoImp intento : intentos) {
            if (intento == null) {
                intento = newIntento;
                lleno = false;
                break;//Digo lo mismo que en Existe Alumno
            }
        }

        if (lleno) {
            intentos = Arrays.copyOf(intentos, intentos.length+10);
            addIntento(idAlumno,idExamen,calificacion);
        }//Toma recursividad jeje
    }

    /*
    PRECONDICIONES: Que exista un alumno con ese ID
    POSTCONDICIONES: Se devuelve un string para imprimir que contiene la informacion
        del alumno y sus medias
     */
    public String getEstadisticasAlumno(int idAlumno) {
        StringBuffer print = new StringBuffer();
        AlumnoImp alumno = null;

        //Obtenemos el alumno del que queremos los datos
        for (AlumnoImp alumnoAct : alumnos) {
            if (alumnoAct.getId() == idAlumno) {
                alumno = alumnoAct;
                break; // El IDE me lo recomendó, pero a los profesores no les mola
            }
        }

        //Calculamos la media de cada asignatura
        double mediaPROG = getMediaAsignaturaDeAlumno(Asignatura.PROG, alumno.getId());
        double mediaFOL = getMediaAsignaturaDeAlumno(Asignatura.FOL, alumno.getId());
        double mediaLM = getMediaAsignaturaDeAlumno(Asignatura.LM, alumno.getId());

        //Añadimos las notas al string buffer junto a la información básica del alumno
        print.append(alumno.toString());
        print.append("\n" +
                "Notas:\n" +
                "\tPROG: "+ mediaPROG +"\n" +
                "\tFOL: "+ mediaFOL +"\n" +
                "\tLM: "+ mediaLM +"\n");

        return print.toString();
    }

    private double getMediaAsignaturaDeAlumno(Asignatura asignatura, int id) {
        int cantidad = 0;
        double sumNotas = 0;
        double media = 0;

        //Iteramos todos los exámenes buscando en los que haya participado este alumno y sumamos
        //las notas guardando la cantidad para hacer la media
        for (IntentoImp intento : intentos) {
            ExamenImp examen = getExamenPorId(intento.getIdExamen());

            if (examen.getAsignatura() == asignatura) {
                sumNotas += intento.getCalificacion();
                cantidad++;
            }
        }
        if (cantidad != 0) {
            media = sumNotas / cantidad;
        } else {
            media = 0;
        }

        return media;
    }

    private ExamenImp getExamenPorId(int idExamen) {
        ExamenImp examenDevuelto = null;

        for (ExamenImp examen : examenes) {
            if (examen.getId() == idExamen) {
                examenDevuelto = examen;
                break;//Digo lo mismo que en otros métodos con un break en un foreach
            }
        }
        return examenDevuelto;
    }
}
