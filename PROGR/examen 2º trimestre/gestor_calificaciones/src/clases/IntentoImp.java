package clases;

import cinterfaces.Intento;

public class IntentoImp implements Intento {

    private int idAlumno;
    private int idExamen;
    private double calificacion;

    public IntentoImp(int idAlumno, int idExamen, double calificacion) {
        this.idAlumno = idAlumno;
        this.idExamen = idExamen;
        this.calificacion = calificacion;
    }

    @Override
    public int getIdAlumno() {
        return idAlumno;
    }

    @Override
    public int getIdExamen() {
        return idExamen;
    }

    @Override
    public double getCalificacion() {
        return calificacion;
    }

    @Override
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}
