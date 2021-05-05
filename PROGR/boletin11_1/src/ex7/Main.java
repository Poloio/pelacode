package ex7;

public class Main {
    public static void main(String[] args) {
        String password = "contrasenia";
        String file = "./src/ex7/text.txt";
        Corrector correct = new Corrector(password);
        correct.correctMayus(password,file);
    }
}
