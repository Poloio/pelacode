import java.time.LocalDate;

public class SuministroImp implements Suministro {

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
}
