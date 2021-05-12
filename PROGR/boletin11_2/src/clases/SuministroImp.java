package clases;

import cinterfaces.Suministro;

import java.time.LocalDate;
import java.util.Objects;

public class SuministroImp implements Suministro, Comparable<SuministroImp> {

    private static Long numeroSuministros;
    private final String codigoBarras;
    private final String nombre;
    private final LocalDate fechaCaducidad;
    private final double precio;

    public SuministroImp(String nombre, LocalDate fechaCaducidad, double precio) {
        numeroSuministros++;
        StringBuilder codigo = new StringBuilder();
        codigo.append(numeroSuministros);
        codigo.append("0".repeat(20 - codigo.toString().length()));

        this.codigoBarras = codigo.toString();
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
    }

    @Override
    public String getCodigoBarras() {
        return codigoBarras;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public int compareTo(SuministroImp o) {
        return codigoBarras.compareTo(o.getCodigoBarras());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuministroImp)) return false;
        SuministroImp that = (SuministroImp) o;
        return codigoBarras.equals(that.codigoBarras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoBarras);
    }

    @Override
    public String toString() {
        return "SuministroImp{" +
                "codigoBarras='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaCaducidad=" + fechaCaducidad +
                ", precio=" + precio +
                '}';
    }
}
