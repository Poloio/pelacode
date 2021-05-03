import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class FileInfo implements Comparable<FileInfo> {

    private String name;
    private boolean directory;
    private long lastModified;
    private long size;

    public FileInfo(String name, boolean directory, long lastModified, long size) {
        this.name = name;
        this.directory = directory;
        this.lastModified = lastModified;
        this.size = size;
    }

    public FileInfo(File file) {
        name = file.getName();
        directory = file.isDirectory();
        lastModified = file.lastModified();
        if (!directory) {
            /* TODO no puedo conseguir el tamaño sin excepciones
            try {
                Path filepath = Paths.get(name);
                size = Files.size(filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            size = 0;
        } else {
            size = 0;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return directory;
    }

    public long getLastModified() {
        return lastModified;
    }

    public long getSize() {
        return size;
    }

    @Override
    public int compareTo(FileInfo o) {
        return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
