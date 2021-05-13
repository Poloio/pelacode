package gestora;

import clases.EPIImp;
import clases.MedicamentoImp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Almacen {

    private static final String ARCHIVO = "./src/gestora/files/AntiPandemia.txt";

    public void crearArchivo() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            try (
                    FileWriter writer = new FileWriter(ARCHIVO);
                    BufferedWriter bufWriter = new BufferedWriter(writer)
            ) {
                int i = 0;
                for (; i < 5; i++) {
                    MedicamentoImp ibupo = new MedicamentoImp("Spididol", LocalDate.now(), 20d,
                            "Pharma", 'C', "Ibuprofeno");
                    bufWriter.write(ibupo.toString());
                    bufWriter.newLine();
                }
                for (; i < 10; i++) {
                    EPIImp pantalla = new EPIImp("Pantalla", LocalDate.now(), 7d, "Dalsy",
                            'C', "Plastico");
                    bufWriter.write(pantalla.toString());
                    bufWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


