public class Jugador {

    private String nombre;
    private int suma;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.suma = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSuma() {
        return suma;
    }

    public void sumarPuntos(int puntos) {
        suma += puntos;
    }
}
