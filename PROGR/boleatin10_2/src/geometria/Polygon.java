package geometria;

public class Polygon {

    private int base;
    private int height;

    public Polygon() {
        base = 1;
        height = 1;
    }

    public Polygon(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public int getArea() {
        return 0;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return "Base: "+ base +"\n" +
                "Height: "+ height +"\n" +
                "Area: "+ getArea() +"\n";
    }
}
