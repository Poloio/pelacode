package ordenador;

public interface Computer {

    // Getters and Setters for memory
    int getMemSize();
    MemType getMemType();
    double getMemClockSpeed();

    // Getters and Setters for Hard Disk
    int getStorageSize();
    StorageType getStorageType();

    // Getters and Setters for CPU
    String getCpuModel();
    double getCpuClockSpeed();
    int getCpuCoreCount();

    // Getters and Setters for computer's price
    double getBasePrice();
    double getFinalPrice();
}
