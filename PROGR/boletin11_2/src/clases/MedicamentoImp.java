package clases;

import cinterfaces.Medicamento;

import java.time.LocalDate;

public class MedicamentoImp extends SuministroImp implements Medicamento {

    private final char presentacion;
    private final String principioActivo;

    public MedicamentoImp(String nombre, LocalDate fechaCaducidad, double precio, char presentacion, String principioActivo) {
        super(nombre, fechaCaducidad, precio);
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
    }

    @Override
    public char getPresentacion() {
        return presentacion;
    }

    @Override
    public String getPrincipioActivo() {
        return principioActivo;
    }

    @Override
    public String toString() {
        return "MedicamentoImp{" +
                "codigoBarras='" + super.getCodigoBarras() + '\'' +
                ", nombre='" + super.getNombre() + '\'' +
                ", fechaCaducidad=" + super.getFechaCaducidad() +
                ", precio=" + super.getPrecio() +
                ", presentacion=" + presentacion +
                ", principioActivo='" + principioActivo + '\'' +
                '}';
    }
}
