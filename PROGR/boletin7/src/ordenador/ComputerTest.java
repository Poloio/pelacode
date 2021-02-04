package ordenador;

public class ComputerTest {
    public static void main(String[] args) {
        Memory mem = new Memory(64,2133, MemType.DDR4);
        Storage store = new Storage(4000, StorageType.NVME);
        CPU cpu = new CPU("Ryzen 5 3600X", 3.7, 6);

        ComputerImp computer = new ComputerImp(store, mem, cpu, 1500);

        System.out.println("Computer base price: "+computer.getBasePrice());
        System.out.println("Computer final price: "+computer.getFinalPrice());
        System.out.println();
        System.out.println("Storage type: "+computer.getStorageType());
        System.out.println("Storage size: "+computer.getStorageSize());
        System.out.println("Memory size: "+computer.getMemSize());
        System.out.println();
        System.out.println("Cpu model: "+computer.getCpuModel());
    }
}
