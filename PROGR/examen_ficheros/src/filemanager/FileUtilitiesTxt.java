package filemanager;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public abstract class FileUtilitiesTxt<T> {

    private File file;
    private String auxPath;


    public FileUtilitiesTxt(String path, String auxPath) {
        assert path != null;
        file = new File(path);
        this.auxPath = auxPath;
    }

    public FileUtilitiesTxt(File file, String auxPath) {
        this.file = file;
        this.auxPath = auxPath;
    }

    /*
    This method writes a T object in a parsable string form
    INPUT: the object to write in the file
    POSTCONDITION: the object is written in the last line of the file
     */
    public void writeObject(T  o) {
        try (
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bfWriter = new BufferedWriter(writer)) {

            bfWriter.write(o.toString());
            bfWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectAux(T  o) {
        try (
                FileWriter writer = new FileWriter(auxPath, true);
                BufferedWriter bfWriter = new BufferedWriter(writer)) {

            bfWriter.write(o.toString());
            bfWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteFile() {
        File aux = new File(auxPath);
        if (file.delete() && aux.renameTo(file)) {
            this.file = aux;
            System.out.println("Master rewritten successfully");
        }
    }

    /*
    This method removes all items where the given attribute matches with the
    provided value.
    INPUT: attribute, the name, and value, the value of teh attribute, both strings.
    PRECONDITIONS: none, it's a dangerously simple
    POSTCONDITIONS: the master record file is overwritten and there is no auxFile left
     */
    public void removeObject(String attribute, String value) {
        File auxFile = new File(auxPath);
        try (
                FileReader reader = new FileReader(file);
                BufferedReader bfReader = new BufferedReader(reader);
                BufferedWriter bfWriter = new BufferedWriter(new FileWriter(auxFile))
                ) {

            String currentLine = bfReader.readLine();

            while (currentLine != null) {
                String attributes = currentLine.split("%")[1];
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

        rewriteFile();
    }

    abstract T readObject(String line);

    /*
    This method returns a List of T objects filled by all the items present on the file.
    PRECONDITIONS: All objects must be parsable by the readObject() function implemented by
        user.
    RETURNS a List of T objects
     */
    public List<T> getContentAsList() {
        List<T> objList = new LinkedList<>();

        try (BufferedReader bfreader = new BufferedReader(new FileReader(file))) {

            String nextObj = bfreader.readLine();
            while (nextObj != null) {
                T obj = readObject(nextObj);
                objList.add(obj);
                nextObj = bfreader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return objList;
    }

    /*
    This method returns the sum value of all attributes found with the given name.
    INPUT: String attribute, the name of the attribute to search.
    OUTPUT: a double representing the sum of all the values.
    PRECONDITIONS: the given attribute must be Double parsable.
     */
    public double sumDoubleValue(String attribute) {
        double sum = 0;

        try (
                FileReader reader = new FileReader(file);
                BufferedReader bfReader = new BufferedReader(reader);
        ) {

            String currentLine = bfReader.readLine();

            while (currentLine != null) {
                String attributes = currentLine.split("%")[1];
                String[] attList = attributes.split(",");

                for (String s : attList) {
                    String[] attSplit = s.split("=");
                    if (attSplit[0].equals(attribute)) {
                        sum += Double.parseDouble(attSplit[1]);
                    }
                }

                currentLine = bfReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public T getTMaxDouble(String attribute) {
        T out = null;
        double max = 0;

        try (
                FileReader reader = new FileReader(file);
                BufferedReader bfReader = new BufferedReader(reader);
        ) {

            String currentLine = bfReader.readLine();
            while (currentLine != null) {
                String attributes = currentLine.split("%")[1];
                String[] attList = attributes.split(",");

                for (String s : attList) {
                    String[] attSplit = s.split("=");
                    if (attSplit[0].equals(attribute)) {
                        double current = Double.parseDouble(attSplit[1]);
                        if (current > max) {
                            max = current;
                            out = readObject(currentLine);
                        }
                    }
                }
                currentLine = bfReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
