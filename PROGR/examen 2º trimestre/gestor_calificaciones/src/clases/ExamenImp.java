package clases;

import cinterfaces.Examen;
import enums.Asignatura;

import java.util.Date;

public class ExamenImp implements Examen {

    public static int numExamenes;

    private int id;
    private Date fecha;
    private Asignatura asignatura;
    private boolean simulacro;

    public ExamenImp(Asignatura asignatura, Date fecha, boolean simulacro) {
        this.asignatura = asignatura;
        this.simulacro = simulacro;
        this.fecha = fecha;
        numExamenes++;
        id = numExamenes;
    }

    @Override
    public Asignatura getAsignatura() {
        return asignatura;
    }

    @Override
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public Date getFecha() {
        return fecha;
    }

    @Override
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean isSimulacro() {
        return simulacro;
    }

    @Override
    public void setSimulacro(boolean simulacro) {
        this.simulacro = simulacro;
    }

    @Override
    public int getId() {
        return id;
    }
}
