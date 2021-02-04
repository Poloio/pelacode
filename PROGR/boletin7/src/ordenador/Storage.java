package ordenador;

public class Storage {

    private int size;
    private StorageType type;

    public Storage(int size, StorageType type) {
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public StorageType getType() {
        return type;
    }
}
