package barcos.gestora;

import barcos.clases.AlquilerImp;
import barcos.clases.Velero;
import org.junit.jupiter.api.Test; 

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void testCalcularAlquiler() {
        Velero velero = new Velero("9-hu-12-000-2",5,2021,2);
        AlquilerImp alquiler = new AlquilerImp("Agapito", "dniuwu", LocalDate.now(),
                LocalDate.now().plusDays(30), 3,velero);

        assertEquals(1248000d, alquiler.getCoste());

        velero = new Velero("9-hu-12-000-2",10,2021,3);
        alquiler = new AlquilerImp("Agapito", "dniuwu", LocalDate.now(),
                LocalDate.now().plusDays(200), 2,velero);

        assertEquals(16480000d, alquiler.getCoste());
    }
}
