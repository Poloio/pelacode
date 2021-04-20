package geometria;

public class Test {

    public static void main(String[] args) {

        SpecialSquare sqr = new SpecialSquare(3, 'o');
        System.out.println(sqr);
        System.out.println(sqr.printSquare());

        Triangle trg = new Triangle(3,2);
        System.out.println(trg);

    }
}
