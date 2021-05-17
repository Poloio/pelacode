package filemanager;

import cinterfaces.Series;
import classes.SeriesImp;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FileSeries extends FileUtilitiesTxt<SeriesImp> {

    public FileSeries(String path, String auxPath) {
        super(path, auxPath);
    }

    public FileSeries(File file, String auxPath) {
        super(file, auxPath);
    }

    @Override
    SeriesImp readObject(String line) {
        String[] splittedLine = line.split("%");
        String type = splittedLine[0];

        String[] attributes = splittedLine[1].split(",");
        String[] attlist = new String[attributes.length];

        for (int i = 0; i < attlist.length; i++) {
            attlist[i] = attributes[i].split("=")[1];
        }

        return new SeriesImp(attlist[0], LocalDate.parse(attlist[1]),
                Double.parseDouble(attlist[2]),
                Integer.parseInt(attlist[3]));
    }

    /*
    This function returns an object of the most repercussion series.
    It iterates all the series present in the file and gets and returns the most
    valuable.
    PRECONDITION is that there has to be at least one series in the file to not throw
        a NullPointerException.
    OUTPUTS a SeriesImp object, the most valued series (repercussion)
     */
    public SeriesImp getMostValuedSeries() {
        List<SeriesImp> list = super.getContentAsList();
        SeriesImp mvp = null;
        double max = 0;

        for (SeriesImp series : list) {
            double currReperc = series.getRepercussion();
            if (currReperc > max) {
                max = currReperc;
                mvp = series;
            }
        }

        return mvp;
    }
}
