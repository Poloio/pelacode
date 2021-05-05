package ex6;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String file1 = "./src/ex6/text1.txt";
        String file2 = "./src/ex6/text2.txt";
        copyContent(file1,file2);
    }

    private static void copyContent(String file1, String file2) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file1));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file2))
        ) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                writer.write(nextLine);
                writer.newLine();
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
