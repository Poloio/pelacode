package ex5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File file = new File("./src/ex5/texto.txt");

        System.out.println(getStatistics(file));
    }

    private static String getStatistics(File file) {
        
        StringBuilder strBuild = new StringBuilder();
        
        List<Integer> lettersPerWord = getLettersPerWord(file);
        List<Integer> wordsPerParagraph = getWordsPerParagraph(file);

        int words = lettersPerWord.size();

        int characters = 0;
        for (Integer letters : lettersPerWord) {
            characters += letters;
        }

        int avgCharPerWord;
        if (words == 0 || characters == 0) avgCharPerWord = 0;
            else avgCharPerWord = characters / words;

        int paragraphs = wordsPerParagraph.size();

        int avgWordsPerPar;
        if (paragraphs == 0 || words == 0) avgWordsPerPar = 0;
            else avgWordsPerPar = words / paragraphs;

        return "Words: "+words+"\n" +
                "Letters: "+characters+"\n" +
                "Paragraphs: "+paragraphs+"\n" +
                "Average Letters per Word: "+avgCharPerWord+"\n" +
                "Average Words per Paragraph: "+avgWordsPerPar+"\n";
    }

    private static List<Integer> getLettersPerWord(File file) {
        List<Character> spacers = new ArrayList<>(Arrays.asList(' ',':','"','.',',','¡','!','¿','?',';','-'));
        List<Integer> lettersPerWord = new LinkedList<>();

        try (FileReader freader = new FileReader(file)) {

            int nextChar = 0;
            boolean inAWord = false;
            int letters = 0;

            while (nextChar != -1) {
                nextChar = freader.read();

                if (spacers.contains((char)nextChar)) {
                    if (inAWord) {
                        lettersPerWord.add(letters);
                        letters = 0;
                        inAWord = false;
                    }
                } else {
                    letters++;
                    inAWord = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lettersPerWord;
    }

    private static List<Integer> getWordsPerParagraph(File file) {
        List<Integer> wordsPerParagraph = new LinkedList<>();
        List<Character> spacers = new ArrayList<>(Arrays.asList(' ',':','"','.',',','¡','!','¿','?',';','-'));

        try (FileReader freader = new FileReader(file)) {

            int nextChar = 0;
            boolean lineEnded = false;
            boolean thereIsWord = false;
            int words = 0;

            while (nextChar != -1) {
                nextChar = freader.read();

                if ((char)nextChar == '\n') {
                    if (words > 0) {
                        wordsPerParagraph.add(words);
                        words = 0;
                    }
                } else if (spacers.contains((char)nextChar) && thereIsWord){
                    words++;
                    thereIsWord = false;
                } else {
                    thereIsWord = true;
                    lineEnded = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordsPerParagraph;
    }

}


