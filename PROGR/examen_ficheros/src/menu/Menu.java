package menu;

import classes.PaidSeriesImp;
import classes.SeriesImp;
import jdk.jshell.execution.Util;
import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;
import java.util.Locale;

public class Menu {
    public static char mainMenu() {
        System.out.println("""
                Choose an option:
                [1] Process series
                [2] Calculate series data
                [3] Manage series
                [4] Exit""");

        return Utilidades.pedirCadena().charAt(0);
    }

    public static char calculatingMenu() {
        System.out.println("""
                Choose an option:
                [1] Total sum of prices
                [2] Most repercussion from a series
                [3] Exit""");

        return Utilidades.pedirCadena().charAt(0);
    }

    public static void printSumPrices(double sumOfPrices) {
        System.out.println("The total sum of the prices is"+sumOfPrices);
    }

    public static void printMVP(SeriesImp mvp) {
        System.out.println("Most repercussion series is "+mvp.getName());
    }

    public static char managmentMenu() {
        System.out.println("""
                Choose an option:
                [1] Add new series
                [2] Remove series
                [3] Exit""");
        
        return Utilidades.pedirCadena().charAt(0);
    }

    public static boolean askPaid() {
        boolean paid = false;
        System.out.println("IS it a paid series?");
        char ispaid = Utilidades.pedirCadena().toUpperCase().charAt(0);
        if (ispaid == 'S') {
            paid = true;
        }
        
        return paid;
    }

    public static SeriesImp askSeries() {
        System.out.println("Enter the name");
        String name = Utilidades.pedirCadena();

        System.out.println("Enter a release date (dd-mm-yyyy)");
        //Input must be parsable or it will throw an exception
        LocalDate releaseDate = LocalDate.parse(Utilidades.pedirCadena());

        System.out.println("Enter the rating");
        double rating = Utilidades.pedirDoble();

        System.out.println("Enter the number of chapters");
        int chapters = Utilidades.pedirEntero();

        return new SeriesImp(name,releaseDate,rating,chapters);
    }

    public static PaidSeriesImp askPaidSeries() {
        System.out.println("Enter the name");
        String name = Utilidades.pedirCadena();

        System.out.println("Enter a release date (dd-mm-yyyy)");
        //Input must be parsable or it will throw an exception
        LocalDate releaseDate = LocalDate.parse(Utilidades.pedirCadena());

        System.out.println("Enter the rating");
        double rating = Utilidades.pedirDoble();

        System.out.println("Enter the number of chapters");
        int chapters = Utilidades.pedirEntero();

        System.out.println("Enter price");
        double price = Utilidades.pedirDoble();

        return new PaidSeriesImp(name,releaseDate,rating,chapters,price);
    }

    public static String askSeriesName() {
        System.out.println("Enter the name of the series you want to delete.");
        return Utilidades.pedirCadena();
    }
}
