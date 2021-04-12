package cinterfaces;

public interface Intento {

    //Identificamos el examen mediante los ids de alumno y examen
    int getIdAlumno();
    int getIdExamen();

    //La calificación es consultable y modificable
    double getCalificacion();
    void setCalificacion(double calificacion);
}
