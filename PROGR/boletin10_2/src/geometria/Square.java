package geometria;

public class Square extends Rectangle{

    public Square() {
        super();
    }

    public Square(int side) {
        super(side, side);
    }

    @Override
    public void setBase(int base) {
        super.setBase(base);
        super.setHeight(base);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setBase(height);
    }
}
