package geometria;

public class SpecialSquare extends Square {

    private char character;

    public SpecialSquare() {
        super(2);
        character = '*';
    }

    public SpecialSquare(int side, char character) {
        super(side);
        this.character = character;
    }

    public String printSquare() {
        StringBuffer print = new StringBuffer();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getBase(); j++) {
                print.append(character);
            }
            print.append("\n");
        }
        return print.toString();
    }
}
