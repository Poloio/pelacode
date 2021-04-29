import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileManager fm = new FileManager();

        File root = new File("./src");
        fm.findFilesForFolder(root);

        File file = new File("./src/files/jaja.txt");
        System.out.println(fm.getFileContentSC(file));
        System.out.println();
        System.out.println(fm.getFileContentFR(file));

        String[] listaA = fm.getContentAsList("./src/files/A.txt");
        System.out.println(listaA[3]);
        String[] listaB = fm.getContentAsList("./src/files/B.txt");
        System.out.println(listaB[3]);
    }
}
