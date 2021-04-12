package main;


import clases.AlumnoImp;
import clases.ExamenImp;
import clases.IntentoImp;
import enums.Asignatura;

import java.util.Arrays;

public class  Gestora {

    private AlumnoImp[] alumnos;
    private ExamenImp[] examenes;
    private IntentoImp[] intentos;
    public static final int MAX_ALUMNOS = 33;

    public Gestora() {
        alumnos = new AlumnoImp[MAX_ALUMNOS];
        examenes = new ExamenImp[10];
        intentos = new IntentoImp[10];
    }

    /*
    Añaden un elemento pasado por parámetros al array correspondiente
     */
    public void add(IntentoImp  newIntento) {
        boolean lleno = true;

        int index = 0;
        for (int i = 0; i < intentos.length && lleno; i++) {
            if (intentos[i] == null) {
                intentos[i] = newIntento;
                lleno = false;
            }
        }

        if (lleno) {
            intentos = Arrays.copyOf(intentos, intentos.length+10);
            add(newIntento);
        }
    }

    public void add(AlumnoImp newAlumno) throws MaxAlumnosException {
        boolean lleno = true;
        for (int i = 0; i < alumnos.length & lleno; i++) {
            if (alumnos[i] == null) {
                alumnos[i] = newAlumno;
                lleno = false;
            }
        }

        if (lleno) {
            throw new MaxAlumnosException("Se ha alcanzado el máximo de alumnos.");
        }
    }

    public void add(ExamenImp newExamen) {
        boolean lleno = true;
        for (int i = 0; i < examenes.length && lleno; i++) {
            if (examenes[i] == null) {
                examenes[i] = newExamen;
                lleno = false;
            }
        }
        
        if (lleno) {
            examenes = Arrays.copyOf(examenes, examenes.length+10);
            add(newExamen);
        }
    }

    public boolean existeExamenConId(int idExamen) {
        boolean existe = false;

        for (ExamenImp examen : examenes) {
            if (examen != null) {
                if (examen.getId() == idExamen) {
                    existe = true;
                    break;// Lo veo feillo pero el IDE lo recomienda, ya me dices que te parece
                }
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
