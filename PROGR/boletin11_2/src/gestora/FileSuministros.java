package gestora;

import clases.SuministroImp;

import java.io.File;

public class FileSuministros extends FileUtilitiesTxt<SuministroImp> {

    public FileSuministros(String path, String auxPath) {
        super(path, auxPath);
    }

    public FileSuministros(File file, String auxPath) {
        super(file, auxPath);
    }

    @Override
    SuministroImp readObject(String line) {
        String[] splittedLine = line.split(" ");
        String type = splittedLine[0];
        String[] attributes = splittedLine[1].split(",");
        String[] attlist = new String[attributes.length];
        for (int i = 0; i < attlist.length; i++) {
            attlist[i] = attributes[i].split("=")[1];
        }

        //TODO arreglar el toString para que la fecha se represente parseable

        // return new SuministroImp(attlist[0],attlist[1], LocalDate.parse(attlist[2]), attlist[3]);
        return null;
    }
}
