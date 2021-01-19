import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collection;
import java.util.HashSet;

public class Handler extends DefaultHandler {


    // Level
    // 1- Juego  2- Partida  3- Jugador  4- Nombre->Jugada  5- Carta  6- Palo->Numero
    private int level;
    private boolean inElement;
    private boolean gameEnded;
    private boolean nameChecked;
    private boolean paloChecked;
    private boolean firstPlayerChecked;
    private boolean firstCardChecked;
    private Player winnerPlayer;
    private Player currentPlayer;
    public static final String[] FIGURAS = {"Sota", "Caballo", "Rey"};

    public Handler() {
        super();
        winnerPlayer = new Player("default");
    }

    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }

    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }

    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
        level++;
        inElement = true;

        switch (level) {
            case 2://When we start a game
                //partida [id]
                System.out.println("Partida con " +att.getLocalName(0) + " " + att.getValue(0));
                break;
            case 3://When we start a player
                System.out.println("\tJugador "+ att.getValue(0));
                break;
            case 5://When we start a card
                System.out.println("\t\tCarta "+ att.getValue(0));
                break;
        }
    }

    @Override
    public void endElement(String uri, String nombre, String nombreC){
        switch (level) {
            case 2://When we finish a game
                System.out.println("Winner of the game : "+ winnerPlayer.getName());
                winnerPlayer = new Player("default");
                break;
            case 3:// When we finish with a player
                nameChecked = false;

                if (currentPlayer.getPoints() > winnerPlayer.getPoints()) {
                    winnerPlayer = currentPlayer;
                }
                System.out.println("Va ganando "+winnerPlayer.getName()+ " con "+ winnerPlayer.getPoints()+" puntos.");

                break;
            case 5://When we finish with a card
                paloChecked = false;
                break;
        }

        inElement = false;
        level--;
    }

    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException {
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]",""); // Quitamos tabuladores y saltos de linea
        if (inElement) {

            switch (level) {
                case 4://Instantiating new player
                    if (!nameChecked) {
                        currentPlayer = new Player(cad);
                    }
                    nameChecked = true;
                    break;

                case 6://Adding points
                    if (paloChecked) {
                        boolean isFigura = false;
                        for (String figura : FIGURAS) {
                            if (figura.equals(cad))
                                isFigura = true;
                        }

                        if (isFigura)
                            currentPlayer.addPoints(0.5);
                        else {
                            currentPlayer.addPoints(Double.parseDouble(cad));
                        }

                        System.out.println("\t\t\t"+cad);
                    }
                    paloChecked = true;
            }

        }
    }
}
