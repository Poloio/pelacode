package barcos.clases;

import barcos.cinterfaces.Barco;

public class BarcoImp implements Barco {

    private String matricula;
    private double eslora;
    private int anioFabricacion;

    public BarcoImp() {
       matricula = "7-HU-2-000-21";
       eslora = 5;
       anioFabricacion = 2021;
    }

    public BarcoImp(String matricula, double eslora, int anioFabricacion) {
        this.matricula = matricula;
        this.eslora = eslora;
        this.anioFabricacion = anioFabricacion;
    }

    @Override
    public double getModulo() {
        return 10 * eslora;
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public double getEslora() {
        return eslora;
    }

    @Override
    public int getAnioFabricacion() {
        return anioFabricacion;
    }
}
