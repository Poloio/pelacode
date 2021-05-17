package gestora;

import cinterfaces.FileUtilities;

import java.io.*;
import java.util.List;

public abstract class FileUtilitiesTxt<T> implements FileUtilities<T> {

    private final File file;
    private final String auxPath;


    public FileUtilitiesTxt(String path, String auxPath) {
        assert path != null;
        file = new File(path);
        this.auxPath = auxPath;
    }

    public FileUtilitiesTxt(File file, String auxPath) {
        this.file = file;
        this.auxPath = auxPath;
    }

    @Override
    public void addObject(T  o) {
        try (
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bfWriter = new BufferedWriter(writer)) {

            bfWriter.write(o.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeObject(String attribute, String value) {
        File auxFile = new File(auxPath);
        try (
                FileReader reader = new FileReader(file);
                BufferedReader bfReader = new BufferedReader(reader);
                BufferedWriter bfWriter = new BufferedWriter(new FileWriter(auxFile))
                ) {

            String currentLine = bfReader.readLine();

            while (currentLine != null) {
                String attributes = currentLine.split(" ")[1];
                String[] attList = attributes.split(",");
                boolean found = false;
                for (String s : attList) {
                    String[] attSplit = s.split("=");
                    if (attSplit[0].equals(attribute) && attSplit[1].equals(value)) {
                        found = true;
                    }
                }
                if (!found) {
                    bfWriter.write(currentLine);
                    bfWriter.newLine();
                }
                currentLine = bfReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fname = file.getName();
        if (file.delete() && auxFile.renameTo(file)) {
            System.out.println("Operation was successful");
        } else {
            System.out.println("Ha habido un problema");
        }
    }

    abstract T readObject(String line);

    @Override
    public List<T> getContentAsList() {
        return null;
    }

    @Override
    public T getByAttribute(String attribute, String value) {
        return null;
    }

    @Override
    public int countCoincidences(String attribute, String value) {
        return 0;
    }

    @Override
    public int sumIntValue(String attribute) {
        return 0;
    }

    @Override
    public double sumDoubleValue(String attribute) {
        return 0;
    }
}
