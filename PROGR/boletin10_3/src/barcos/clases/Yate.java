package barcos.clases;

public class Yate extends BarcoImp {

    private int nCamarotes;

    public Yate() {
        super();
        nCamarotes = 2;
    }

    public Yate(String matricula, double eslora, int anioFabricacion, int nCamarotes) {
        super(matricula, eslora, anioFabricacion);
        this.nCamarotes = nCamarotes;
    }

    @Override
    public double getModulo() {
        return super.getModulo() + nCamarotes;
    }

    public int getnCamarotes() {
        return nCamarotes;
    }
}
