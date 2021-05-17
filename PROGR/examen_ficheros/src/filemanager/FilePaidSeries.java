package filemanager;

import classes.PaidSeriesImp;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class FilePaidSeries extends FileUtilitiesTxt<PaidSeriesImp> {

    public FilePaidSeries(String path, String auxPath) {
        super(path, auxPath);
    }

    public FilePaidSeries(File file, String auxPath) {
        super(file, auxPath);
    }

    @Override
    PaidSeriesImp readObject(String line) {
        String[] splittedLine = line.split(" ");
        String type = splittedLine[0];

        String[] attributes = splittedLine[1].split(",");
        String[] attlist = new String[attributes.length];

        for (int i = 0; i < attlist.length; i++) {
            attlist[i] = attributes[i].split("=")[1];
        }

        return new PaidSeriesImp(attlist[0], LocalDate.parse(attlist[1]),Double.parseDouble(attlist[2]),
                Integer.parseInt(attlist[3]), Double.parseDouble(attlist[4]));
    }

    public void sortFile() {
        List<PaidSeriesImp> objList = getContentAsList();
        Collections.sort(objList);
        for (PaidSeriesImp pd : objList) {
            super.writeObjectAux(pd);
        }
        super.rewriteFile();
    }


}
