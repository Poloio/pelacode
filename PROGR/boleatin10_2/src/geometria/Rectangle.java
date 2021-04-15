package geometria;

public class Rectangle extends Polygon {

    private int base;
    private int height;

    public Rectangle() {
        super(1,1);
    }

    public Rectangle(int base, int height) {
        super(base, height);
    }
}
