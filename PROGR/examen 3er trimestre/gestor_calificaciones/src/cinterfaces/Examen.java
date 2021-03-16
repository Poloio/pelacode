package cinterfaces;

import enums.Asignatura;

import java.util.Date;

public interface Examen {

    int getId();

    //Fecha de realización
    Date getFecha();
    void setFecha(Date fecha);

    //Asignatura de la que se ha hecho el examen
    Asignatura getAsignatura();
    void setAsignatura(Asignatura asignatura);

    //Indica si es un simulacro para saber si tenerlo en cuenta en las medias
    boolean isSimulacro();
    void setSimulacro(boolean isSimulacro);

}
