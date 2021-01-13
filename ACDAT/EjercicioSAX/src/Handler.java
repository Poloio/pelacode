import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    boolean inElement = false;

    public Handler() {
        super();
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
        inElement = true;
    }
    @Override
    public void endElement(String uri, String nombre, String nombreC){
        inElement = false;
    }
    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException {
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]",""); // Quitamos tabuladores y saltos de linea
        System.out.println("\t\t" + cad);
    }
}
