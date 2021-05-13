package clases;

import cinterfaces.Suministro;

import java.time.LocalDate;
import java.util.Objects;

public class SuministroImp implements Suministro, Comparable<SuministroImp> {

    private static Long numeroSuministros = 0L;
    private final String codigoBarras;
    private final String nombre;
    private final LocalDate fechaCaducidad;
    private final double precio;
    private final String fabricante;

    public SuministroImp(String nombre, LocalDate fechaCaducidad, double precio, String fabricante) {
        numeroSuministros++;
        this.codigoBarras = String.format("%020d", numeroSuministros);
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
        this.fabricante = fabricante;
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
    public String getFabricante() {
        return this.fabricante;
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
        return "SuministroImp " +
                "codigoBarras=" + codigoBarras +
                ",nombre=" + nombre +
                ",fechaCaducidad=" + fechaCaducidad +
                ",precio=" + precio +
                ",fabricante=" + fabricante;
    }
}
