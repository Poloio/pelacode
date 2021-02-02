package ejercicio4;

public interface Piratagochi {

    /*
    PRE: none
    POST: IF isHungry THEN isHungry -> false; ELSE decrement happiness
    */
    void eat();

    /*
    INPUT: int hoursSleeping - The amount of in-game hours we want gochi to sleep.
    PRECONDITIONS: hoursSleeping is positive
    POSTCONDITIONS: Recover 'hoursSleeping' points of stamina until full, after full, remove remaining hours as points
        to happiness
    */
    void sleep(int hoursSleeping);

    /*
    INPUT: int hoursPlaying - The amount of in-game hours we want gochi to sleep.
    PRE: hoursPlaying is positive
    POST: recover hoursPlaying points of happiness and lose the same of stamina, and become hungry. If hungry,
        instead lose hoursPlaying points of happiness.
     */
    void play(int hoursPlaying);

    /*
    PRE: none
    POST: Current Gochi's state is printed on console.
     */
    String getState();

    /*
    OUTPUT: String name - Our gochi's name.
    PRE: none
    POST: returns Gochi's name as a String
     */
    String getName();

    /*
    OUTPUT: boolean isHungry - true if gochi's hungry.
    PRE: none
    POST: returns if Gochi's hungry
     */
    boolean isHungry();

    /*
    OUTPUT: int stamina - current stamina points.
    PRE: none
    POST: returns Gochi's stamina points as an Int
     */
    int getStamina();

    /*
    OUTPUT: int happiness - returns gochi's happiness points.
    PRE: none
    POST: returns Gochi's happiness points as an Int
     */
    int getHappiness();
}
