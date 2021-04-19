package geometria;

public class Rectangle implements Polygon {

    private int base;
    private int height;

    public Rectangle() {
        base = 1;
        height = 1;
    }

    public Rectangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public int getArea() {
        return base * height;
    }

    public String toString() {
        return "Base: "+ base +"\n" +
                "Height: "+ height +"\n" +
                "Area: "+ getArea() +"\n";
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
