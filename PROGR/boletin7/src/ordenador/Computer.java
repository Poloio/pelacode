package ordenador;

public interface Computer {

    // Getters for memory
    int getMemSize();
    MemType getMemType();
    double getMemClockSpeed();

    // Getters for Hard Disk
    int getStorageSize();
    StorageType getStorageType();

    // Getters for CPU
    String getCpuModel();
    double getCpuClockSpeed();
    int getCpuCoreCount();

    // Getters for computer's price
    double getBasePrice();
    double getFinalPrice();
}
