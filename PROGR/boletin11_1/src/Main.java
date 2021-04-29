import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileManager fm = new FileManager();

        File root = new File("./src");
        fm.findFilesForFolder(root);

        File file = new File("./src/jaja.txt");
        System.out.println(fm.getFileContentSC(file));
        System.out.println();
        System.out.println(fm.getFileContentFR(file));
    }
}
