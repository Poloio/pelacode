package ex7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Corrector {

    private String password;

    public Corrector (String password) {
        this.password = password;
    }

    public void correctMayus(String password, String filePath) {
        if (password.equals(this.password)) {

            List<Character> pointIndicators = new ArrayList<>(Arrays.asList('.', '!', '?', ';'));

            try (
                    FileReader reader = new FileReader(filePath);
                    FileWriter writer = new FileWriter("./src/ex7/correctedText.txt")
            ) {

                boolean nextIsMayus = true;
                int next = reader.read();
                while (next != -1) {

                    char nextChar = (char) next;

                    if (pointIndicators.contains(nextChar)) {
                        nextIsMayus = true;
                        writer.write(nextChar);

                    } else if (nextIsMayus && nextChar >= 'a' && nextChar <= 'z') {
                        char corrected = String.valueOf(nextChar).toUpperCase().charAt(0);
                        writer.write(corrected);
                        nextIsMayus = false;

                    } else {
                        writer.write(nextChar);
                    }
                    next = reader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("contra incorrecta uwu");
        }
    }
}
