package barcos.cinterfaces;

import barcos.clases.BarcoImp;

import java.time.LocalDate;

public interface Alquiler {

    String getNombreCliente();
    String getDni();
    LocalDate getFechaInicio();
    void setFechaInicio(LocalDate fecha);
    LocalDate getFechaFin();
    void setFechaFin(LocalDate fecha);
    int getPosicion();
    BarcoImp getBarco();

    String getMatriculaBarco();
    double getEsloraBarco();
    int getAnioBarco();

    double getCoste();
}
