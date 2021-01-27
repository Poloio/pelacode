import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    int indentation = 0;
    /* Indentation para las partidas
    1 - Juego
    2 - Partida
    3 - Jugador
    4 - Nombre/Jugada
    5 - Carta
     */
    boolean jugada = false;
    boolean partidaTerminada;
    Jugador jugadorAnterior;
    Jugador jugadorActual;
    String  nombreGanador;

    public Handler() {
        super();
    }

    @Override
    public void startDocument(){
        System.out.println("Leyendo documento XML");
    }
    @Override
    public void endDocument(){
        System.out.println("Cerrando documento XML");
    }

    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
        indentation++;
        if (indentation == 4) {
            if (nombreC.equals("jugada"))
                jugada = true;
        } else
            jugada = false;
    }

    @Override
    public void endElement(String uri, String nombre, String nombreC){
        indentation--;
        if (indentation == 1)
            System.out.println("Fin de partida");
    }

    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException {
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]",""); // Quitamos tabuladores y saltos de linea

        if (indentation == 4) {
            if (!jugada) {
                jugadorActual = new Jugador(cad);
            }
        }

        if (partidaTerminada) {
            System.out.println("El ganador es "+nombreGanador);
        }
        partidaTerminada = false;



    }
}
