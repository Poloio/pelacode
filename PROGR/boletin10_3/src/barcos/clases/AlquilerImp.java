package barcos.clases;

import barcos.cinterfaces.Alquiler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AlquilerImp implements Alquiler {

    private String nombreCliente;
    private String dni;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int posicion;
    private BarcoImp barco;
    private final double VALOR_FIJO = 800;

    public AlquilerImp(String nombreCliente, String dni, LocalDate fechaInicio, LocalDate fechaFin, int posicion, BarcoImp barco) {
        this.nombreCliente = nombreCliente;
        this.dni = dni;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.posicion = posicion;
        this.barco = barco;
    }

    @Override
    public double getCoste() {
        int dias = (int)fechaInicio.until(fechaFin, ChronoUnit.DAYS);
        return dias * barco.getModulo() * VALOR_FIJO;
    }

    @Override
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    @Override
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    @Override
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String getNombreCliente() {
        return nombreCliente;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public int getPosicion() {
        return posicion;
    }

    @Override
    public BarcoImp getBarco() {
        return barco;
    }

    @Override
    public String getMatriculaBarco() {
        return barco.getMatricula();
    }

    @Override
    public double getEsloraBarco() {
        return barco.getEslora();
    }

    @Override
    public int getAnioBarco() {
        return barco.getAnioFabricacion();
    }
}
