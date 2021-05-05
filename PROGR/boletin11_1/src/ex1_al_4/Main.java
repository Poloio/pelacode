package ex1_al_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        System.out.println("Ex - 1");
        File root = new File("./src");
        fm.findFilesForFolder(root);

        System.out.println("\nEx - 2");
        File file = new File("./src/files/jaja.txt");
        System.out.println("Mediante Scanner");
        System.out.println(fm.getFileContentSC(file));
        System.out.println("Mediante FileReader");
        System.out.println(fm.getFileContentFR(file));

        System.out.println("\nEx - 3 y 4");
        System.out.println("Contenidos de src ordenados alfabeticamente");
        for (FileInfo info : fm.getContentAsList("./src")) {
            System.out.println(info.getName());
        }

        System.out.println("Ex 4_2");
        writeInputInto("./src/files/entradaPorTeclado.txt");
    }

    private static void writeInputInto(String filePath) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        try (FileWriter file = new FileWriter(filePath)) {

            while (!input.equals("x")) {

                input = sc.nextLine();
                if (!input.equals("x")) {
                    file.write(input + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
