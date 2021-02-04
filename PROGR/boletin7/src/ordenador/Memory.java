package ordenador;

public class Memory {

    private int size;
    private double clockSpeed;
    private MemType type;

    public Memory(int size, double clockSpeed, MemType type) {
        this.size = size;
        this.clockSpeed = clockSpeed;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public MemType getType() {
        return type;
    }
}
