package gestora;

import cinterfaces.FileUtilities;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public abstract class FileUtilitiesBin<T> implements FileUtilities<T>
{

    private final File file;
    private final String auxPath;


    public FileUtilitiesBin (String path, String auxPath) {
        assert path != null;
        file = new File(path);
        this.auxPath = auxPath;
    }

    public FileUtilitiesBin (File file, String auxPath) {
        this.file = file;
        this.auxPath = auxPath;
    }

    @Override
    public void addObject(T o) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file,true))) {

            out.writeObject(o);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeObject(String attribute, String value) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            Object nextObj = in.readObject();
            while (nextObj != null) {
                //TODO revisar atributos en el tostring o algo
                //update LCV
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getContentAsList() {
        List<T> objList = new LinkedList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            Object nextObj = in.readObject();
            while (nextObj != null) {
                objList.add((T)nextObj);
                nextObj = in.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objList;
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
