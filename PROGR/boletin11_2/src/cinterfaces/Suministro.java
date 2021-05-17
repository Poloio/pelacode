package cinterfaces;

import java.io.Serializable;
import java.time.LocalDate;

public interface Suministro extends Serializable, Comparable<Suministro>
{

    String getCodigoBarras();
    String getNombre();
    LocalDate getFechaCaducidad();
    double getPrecio();
    String getFabricante();
}
