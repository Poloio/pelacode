package cerrojo;

public class LockTest {
    public static void main(String[] args) {
        LockImp lock1 = new LockImp(728);
        LockImp lock2 = new LockImp(81293);

        lock1.changeCombination(729, 999);
        lock1.changeCombination(728, 828);

        lock1.open(828);
    }
}
