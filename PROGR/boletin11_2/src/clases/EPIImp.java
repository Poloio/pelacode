package clases;

import cinterfaces.EPI;

import java.time.LocalDate;

public class EPIImp extends SuministroImp implements EPI {

    private final char parteCuerpo;
    private final String material;

    public EPIImp(String nombre, LocalDate fechaCaducidad, double precio, char parteCuerpo, String material) {
        super(nombre, fechaCaducidad, precio);
        this.parteCuerpo = parteCuerpo;
        this.material = material;
    }

    @Override
    public char getParteCuerpo() {
        return parteCuerpo;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "EPIImp{" +
                "codigoBarras='" + super.getCodigoBarras() + '\'' +
                ", nombre='" + super.getNombre() + '\'' +
                ", fechaCaducidad=" + super.getFechaCaducidad() +
                ", precio=" + super.getPrecio() +
                ", parteCuerpo=" + parteCuerpo +
                ", material='" + material + '\'' +
                '}';
    }
}
