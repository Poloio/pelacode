package excepciones;

public class JugadorNoExisteException extends Exception {
    public JugadorNoExisteException(String msg) {
       super(msg);
    }
}
