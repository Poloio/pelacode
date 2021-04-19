package geometria;

public class Triangle implements Polygon {

    private int base;
    private int height;

    public Triangle() {
        base = 1;
        height = 1;
    }

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public String toString() {
        return "Base: "+ base +"\n" +
                "Height: "+ height +"\n" +
                "Area: "+ getArea() +"\n";
    }

    double getArea() {
        return base * height / 2d;
    }

    @Override
    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
