package clases;

import cinterfaces.Medicamento;

import java.time.LocalDate;

public class MedicamentoImp extends SuministroImp implements Medicamento {

    private final char presentacion;
    private final String principioActivo;

    public MedicamentoImp(String nombre, LocalDate fechaCaducidad, double precio, String fabricante, char presentacion, String principioActivo) {
        super(nombre, fechaCaducidad, precio, fabricante);
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
        return "MedicamentoImp " +
                "codigoBarras=" + super.getCodigoBarras() +
                ",nombre=" + super.getNombre() +
                ",fechaCaducidad=" + super.getFechaCaducidad() +
                ",precio=" + super.getPrecio() +
                ",fabricante=" + super.getFabricante() +
                ",presentacion=" + presentacion +
                ",principioActivo=" + principioActivo;
    }
}
