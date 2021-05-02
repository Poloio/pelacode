import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {

    public FileManager() {}

    public void findFilesForFolder(final File directory) {
        for (File fileEntry : Objects.requireNonNull(directory.listFiles())) {
            if (fileEntry.isDirectory()) {
                findFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    //Lee el archivo caracter a caracter para una representación exacta del texto.
    String getFileContentFR(final File file) {
        StringBuilder buffer = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            int i;
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
        StringBuilder builder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    String[] getContentAsList(String path) {
        List<String> outList = new LinkedList<>();
        int palabras = 1; //Necesaria para sacar un array de String del método

        try (FileReader target = new FileReader(path)) {
            int i;
            StringBuilder strbuild = new StringBuilder();

            //Mientras no se encuentre el fin del archivo
            while ((i = target.read()) != -1) {
                //SI es un espacio o salto de linea se guarda la palabra;
                if ((char)i == '\n' || (char)i == ' ') {
                    outList.add(strbuild.toString());
                    strbuild.setLength(0);
                    palabras++;
                } else {
                    strbuild.append((char)i);
                }
            }
            //Cuando se llega al fin del archivo se añade la última palabra
            outList.add(strbuild.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outList.toArray(new String[palabras]);
    }
}
