package cerrojo;
import java.util.Random;

public class LockImp implements Lock {

    private int combination;
    private boolean closed;

    public LockImp(int combination) {
        Random rnd = new Random();

        if (combination >= 0 && combination < 1000) {
            this.combination = combination;
            System.out.println("Combination for lock set as "+this.combination);
        } else {
            this.combination = rnd.nextInt(1000);
            System.out.println("Combination for lock not valid, it was set instead as "+this.combination);
        }
        closed = true;
    }

    public void changeCombination(int currentCombination, int newCombination) {

        if (currentCombination == this.combination) {

            if (newCombination >= 0 && newCombination < 1000) {
                this.combination = newCombination;
                System.out.println("New combination set as " + this.combination);
            } else {
                System.out.println("Invalid new combination");
            }

        } else {
            System.out.println("You must know the combination to change it.");
        }
    }

    public void open(int combination) {

        if (closed) {
            if (combination == this.combination) {
                closed = false;
                System.out.println("Lock opened!");
            } else {
                System.out.println("Wrong combination");
            }
        } else {
            System.out.println("The lock was already open");
        }
    }

    public boolean isClosed() {
        return closed;
    }

}
