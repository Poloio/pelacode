package ordenador;

public class CPU {

    private String model;
    private double clockSpeed;
    private int coreCount;

    public CPU(String model, double clockSpeed, int coreCount) {
        this.model = model;
        this.clockSpeed = clockSpeed;
        this.coreCount = coreCount;
    }

    public String getModel() {
        return model;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public int getCoreCount() {
        return coreCount;
    }
}
