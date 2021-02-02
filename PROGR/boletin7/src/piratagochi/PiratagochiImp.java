package piratagochi;
import java.util.Random;

public class PiratagochiImp implements Piratagochi {

    String name;
    boolean isHungry;
    int stamina;
    int happiness;

    public PiratagochiImp(String name) {
        Random r = new Random();

        this.name = name;
        isHungry = false;
        stamina = r.nextInt(10)+1;
        happiness = r.nextInt(10)+1;
    }

    @Override
    public void eat() {

        if (isHungry) {
            isHungry = false;
        } else {
            happiness--;
        }
    }

    @Override
    public void sleep(int hoursSleeping) {

        for (int i = 0; i < hoursSleeping; i++) {
            if (stamina == 10) removeHappiness();
            addStamina();
        }

        System.out.println("\n" + name + " slept for " + hoursSleeping + " hours");
    }

    @Override
    public void play(int hoursPlaying) {

        for (int i = 0; i < hoursPlaying; i++) {
            removeStamina();
            if (isHungry) {
                removeHappiness();
            } else {
                addHappiness();
            }
        }

        System.out.println("\n" + name + " played for " + hoursPlaying + " hours");
    }

    @Override
    public String getState() {

        StringBuilder state = new StringBuilder();
        state.append("\n").append(name).append(" - \n");

        if (stamina < 5) state.append("I'm tired...\n");
        if (isHungry) state.append("I'm hungry!\n");
        if (happiness < 5) state.append("I'm sad :(");

        return state.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isHungry() {
        return isHungry;
    }


    @Override
    public int getStamina() {
        return stamina;
    }

    private void addStamina() {
        if (stamina < 10) {
            stamina++;
        }
    }

    private void removeStamina() {
        if (stamina > 1) {
            stamina--;
        }
    }

    @Override
    public int getHappiness() {
        return happiness;
    }

    public void addHappiness() {
        if (happiness < 10) {
            happiness++;
        }
    }

    public void removeHappiness() {
        if (happiness > 1) {
            happiness--;
        }
    }
}
