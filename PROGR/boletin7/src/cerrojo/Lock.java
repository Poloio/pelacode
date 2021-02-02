package cerrojo;

public interface Lock {

    /*
    INPUT: int currentCombination - guess for the current combination provided
        int newCombination - the new combination (duh)
    PRE: currentCombination and newCombination are numbers > 0 and <= 999
    POST: if currentCombination == this.combination THEN change to newCombination
     */
    void changeCombination(int currentCombination, int newCombination);

    /*
    OUTPUT: boolean isClosed - true if the lock is closed
     */
    boolean isClosed();

    /*
    INPUT: int combination - user guess for the lock's combination.
    PRE: combination is a number between 0 and 999
    POST: IF the combination is correct, isLocked = false.
     */
    void open(int combination);
}
