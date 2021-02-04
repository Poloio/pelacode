package ordenador;

public class ComputerImp implements Computer {

    private Storage storage;
    private Memory memory;
    private CPU cpu;

    public static final int IVA = 21;
    private double basePrice;

    public ComputerImp(Storage storage, Memory memory, CPU cpu, double basePrice) {
        this.storage = storage;
        this.memory = memory;
        this.cpu = cpu;
        this.basePrice = basePrice;
    }

    public int getStorageSize() {
        return storage.getSize();
    }
    public StorageType getStorageType() {
        return storage.getType();
    }


    public int getMemSize() {
        return memory.getSize();
    }
    public double getMemClockSpeed() {
        return memory.getClockSpeed();
    }
    public MemType getMemType() {
        return memory.getType();
    }

    public String getCpuModel() {
        return cpu.getModel();
    }
    public double getCpuClockSpeed() {
        return cpu.getClockSpeed();
    }
    public int getCpuCoreCount() {
        return cpu.getCoreCount();
    }


    public double getBasePrice() {
        return basePrice;
    }
    public double getFinalPrice() {
        double tax = basePrice * (IVA/100D);
        return basePrice + tax;
    }
}
