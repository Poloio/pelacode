package tests;

import filemanager.FilePaidSeries;
import filemanager.FileSeries;

public class Test {


    public static void main(String[] args) {
        //Sumatorio de precios
        FilePaidSeries fps = new FilePaidSeries(".\\src\\filemanager\\series.txt",
                ".\\src\\filemanager\\seriesAux.txt");
        System.out.println(fps.sumDoubleValue("price"));

        //La serie con más repercusión
        FileSeries fs = new FileSeries(".\\src\\filemanager\\series.txt",
                ".\\src\\filemanager\\seriesAux.txt");
        System.out.println(fs.getMostValuedSeries());

        //Quitar una serie (lo tenia hecho y solo he tenido que pedir el nombre)
        fs.removeObject("name","La masacre de raul");

    }
}
