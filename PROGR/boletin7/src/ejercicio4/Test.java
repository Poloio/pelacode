package ejercicio4;

public class Test {
    public static void main(String[] args) {

        PiratagochiImp gochi1 = new PiratagochiImp("Pepe");
        PiratagochiImp gochi2 = new PiratagochiImp("Pablo");

        System.out.println(gochi1.getState());
        System.out.println(gochi2.getState());

        gochi1.sleep(10);
        System.out.println(gochi1.getState());

    }
}
