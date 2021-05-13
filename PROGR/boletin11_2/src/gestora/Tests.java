package gestora;

public class Tests {
    public static void main(String[] args) {

        FileSuministros fs = new FileSuministros("./src/gestora/files/AntiPandemia.txt",
                "./src/gestora/files/AntiPandemiaAUX.txt");

        Almacen alm = new Almacen();
        alm.crearArchivo();
        fs.removeObject("nombre", "Pantalla");
    }
}
