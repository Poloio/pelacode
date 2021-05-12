package cinterfaces;

import java.time.LocalDate;

public interface Suministro {

    String getCodigoBarras();
    String getNombre();
    LocalDate getFechaCaducidad();
    double getPrecio();
}
