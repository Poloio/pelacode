package gestora;

import clases.EPIImp;
import clases.MedicamentoImp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Almacen {

    private static final String ARCHIVO = "./src/gestora/AntiPandemia.txt";

    public void LlenarAlmacen() {
        MedicamentoImp ibupo = new MedicamentoImp("Spididol", LocalDate.now(), 20d, 'C',
                                                    "Ibuprofeno");
        EPIImp pantalla = new EPIImp("Pantalla", LocalDate.now(), 7d, 'C', "Plastico");

        try (
                FileWriter writer = new FileWriter(ARCHIVO);
                BufferedWriter bufWriter = new BufferedWriter(writer)
                ) {
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
