package ex8;

public class Letter implements Comparable<Letter> {

    private char letter;
    private int quantity;

    public Letter(char letter, int quantity) {
        this.letter = letter;
        this.quantity = quantity;
    }

    public char getLetter() {
        return letter;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Letter o) {
        return o.quantity - this.quantity;
    }
}
