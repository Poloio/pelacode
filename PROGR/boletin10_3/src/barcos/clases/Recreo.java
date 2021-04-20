package barcos.clases;

public class Recreo extends BarcoImp {

    private int caballos;

    public Recreo() {
        super();
        caballos = 300;
    }

    public Recreo(String matricula, double eslora, int anioFabricacion, int caballos) {
        super(matricula, eslora, anioFabricacion);
        this.caballos = caballos;
    }

    @Override
    public double getModulo() {
        return super.getModulo() + caballos;
    }

    public int getCaballos() {
        return caballos;
    }
}
