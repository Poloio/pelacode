package barcos.clases;

public class Velero extends BarcoImp {

    private int nMastiles;

    public Velero() {
        super();
        nMastiles = 1;
    }

    @Override
    public double getModulo() {
        return super.getModulo() + nMastiles;
    }

    public Velero(String matricula, double eslora, int anioFabricacion, int nMastiles) {
        super(matricula, eslora, anioFabricacion);
        this.nMastiles = nMastiles;
    }

    public int getnMastiles() {
        return nMastiles;
    }
}
