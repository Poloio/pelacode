package ex5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        File file = new File("./src/ex5/texto.txt");

        System.out.println(getStatistics(file));
    }

    private static String getStatistics(File file) {
        int words = 0;
        int characters = 0;
        int paragraphs = 0;
        int avgCharPerWord;
        int avgWordsPerPar;

        try (FileReader freader = new FileReader(file)) {
            int nextChar = 0;
            boolean lineEnded = false;
            while (nextChar != -1) {

                nextChar = freader.read();
                switch ((char)nextChar) {
                    case ' ':
                        words++;
                        break;
                    case '\n':
                        if (lineEnded) {
                            paragraphs++;
                        } else {
                            lineEnded = true;
                        }
                        break;
                    default:
                        characters++;
                }

                caca(new File("./"))
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}


