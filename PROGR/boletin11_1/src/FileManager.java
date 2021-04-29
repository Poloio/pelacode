import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public FileManager() {}

    public void findFilesForFolder(final File directory) {
        for (File fileEntry : directory.listFiles()) {
            if (fileEntry.isDirectory()) {
                findFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    //Lee el archivo caracter a caracter para una representación exacta del texto.
    String getFileContentFR(final File file) {
        StringBuffer buffer = new StringBuffer();
        try {
            FileReader reader = new FileReader(file);
            int i = 0;
            while ((i=reader.read()) != -1){
                buffer.append((char) i);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    //Lee el archivo línea a línea y pueden parsearse primitivos
    String getFileContentSC(final File file) {
        StringBuffer buffer = new StringBuffer();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
