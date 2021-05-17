package manager;

import classes.PaidSeriesImp;
import classes.SeriesImp;
import filemanager.FilePaidSeries;
import filemanager.FileSeries;
import menu.Menu;
import java.io.File;
import java.time.LocalDate;

public class Main {

    public static final String MASTER = ".\\src\\filemanager\\series.txt";
    public static final String AUX = ".\\src\\filemanager\\seriesAux.txt";

    public static void main(String[] args) {

        initFile();
        //MAIN menu
        boolean exit = false;
        do {
            char option = Menu.mainMenu();
            switch (option) {
                case '1' -> printSection();
                case '2' -> calculatingSection();
                case '3' -> managementSection();
                case '4' -> {
                    exit = true;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please enter a valid option.");
            }
        } while (!exit);

    }

    private static void printSection() {
        System.out.println("Section under construction");
    }

    //CALCULATING MENU: Only for program condition
    private static void calculatingSection() {

        boolean exit = false;
        do {
            char option = Menu.calculatingMenu();
            switch (option) {
                case '1':// Prices sum
                    double sumOfPrices = getPriceSum();
                    Menu.printSumPrices(sumOfPrices);
                    break;
                case '2':// Most valued series
                    SeriesImp mvp = getMostValuedSeries();
                    Menu.printMVP(mvp);
                    break;
                case '3':// Exit
                    exit = true;
                default:
                    System.out.println("Please enter a valid option.");
            }
        } while (!exit);

    }

    // This acts as a wrapper for FileSeries instantiation and function call
    private static SeriesImp getMostValuedSeries() {
        FileSeries fs = new FileSeries(MASTER,AUX);
        return fs.getMostValuedSeries();
    }

    // This acts as a wrapper for FilePaidSeries instantiation and function call
    private static double getPriceSum() {
        FilePaidSeries fps = new FilePaidSeries(MASTER,AUX);
        return fps.sumDoubleValue("price");
    }

    //MANAGEMENT MENU
    private static void managementSection() {
        boolean exit = false;
        do {
            char option = Menu.managmentMenu();

            switch (option) {
                case '1':
                    addSeries();
                    break;
                case '2':
                    removeSeries();
                case '3':
                    exit = true;
                default:
                    System.out.println("Please enter a valid option.");
            }
        } while (!exit);
    }

    //Acts as a wrapper for data collecting and fs instantiation
    private static void removeSeries() {
        String name = Menu.askSeriesName();
        FileSeries fs = new FileSeries(MASTER,AUX);
        fs.removeObject("name", name);
    }

    private static void addSeries() {
        boolean paid = Menu.askPaid();
        if (paid) {
            PaidSeriesImp newSeries = Menu.askPaidSeries();
            FilePaidSeries fps = new FilePaidSeries(MASTER,AUX);
            fps.writeObject(newSeries);
        } else {
            SeriesImp newSeries = Menu.askSeries();
            FileSeries fs = new FileSeries(MASTER,AUX);
            fs.writeObject(newSeries);
        }
    }

    public static void initFile() {

        FileSeries fs = new FileSeries(".\\src\\filemanager\\series.txt",
                ".\\src\\filemanager\\seriesAux.txt");

        FilePaidSeries fps = new FilePaidSeries(".\\src\\filemanager\\series.txt",
                ".\\src\\filemanager\\seriesAux.txt");

        File file = new File(".\\src\\filemanager\\series.txt");
        if (!file.exists()) {
            fs.writeObject(new SeriesImp("La masacre de raul", LocalDate.now(), 6d, 24));
            fs.writeObject(new SeriesImp("La masacre de raul 2", LocalDate.now(), 9d, 12));
            fps.writeObject(new PaidSeriesImp("La masacre de raul de pago", LocalDate.now(), 10d,
                    6, 400d));
            fps.writeObject(new PaidSeriesImp("Amar es para siempre shalalala", LocalDate.now(), 1d,
                    9999, 1.69));
        }
    }
}
