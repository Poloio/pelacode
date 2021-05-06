package ex8;

import com.sun.jdi.Field;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String file = "./src/ex8/text.txt";
        Letter[] letters = lettersInOrder(file);
        for (Letter letter : letters) {
            System.out.print(letter.getLetter());
        }
    }

    private static Letter[] lettersInOrder(String file) {

        Letter[] letters = getLettersRepetition(file);
        Arrays.sort(letters);
        return letters;
    }

    private static Letter[] getLettersRepetition(String file) {
        String abcdary = "abcdefghijklmnñopqrstuvwxyz";

        //Mapa con la letra de Key y el contador de Value
        Map<Character,Integer> lettersMap = fillMap(abcdary);

        try (FileReader reader = new FileReader(file)){
            int next = reader.read();
            while (next != -1) {
                char letter = (char) next;
                if (lettersMap.containsKey(letter)) {
                    int counter = lettersMap.get(letter);
                    lettersMap.replace(letter,++counter);
                }
                next = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapToArray(lettersMap);
    }

    private static Map<Character, Integer> fillMap(String abcdary) {
        Map<Character, Integer> map = new HashMap<>();
        for (char letter : abcdary.toCharArray()) {
            map.put(letter,0);
        }
        return map;
    }

    private static Letter[] mapToArray(Map<Character, Integer> lettersMap) {
        Letter[] letterArray = new Letter[lettersMap.size()];

        int counter = 0;
        for (Map.Entry<Character,Integer> entry : lettersMap.entrySet()) {
            letterArray[counter] = new Letter(entry.getKey(),entry.getValue());
            counter++;
        }
        return letterArray;
    }
}
