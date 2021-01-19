public class Player {

    private String name;
    private double points;
    private boolean pasado;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public double getPoints() {
        return points;
    }

    public void addPoints(double cardPoints) {
        if (!pasado) {
            points += cardPoints;
        }
        if (points > 7) {
            pasado = true;
            points = 0;
        }
    }
}
