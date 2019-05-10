package exception;
/** Exception called when an instance of {@link pec.PEC} has no events but nextEvent() gets called*/
public class NoEventException extends Exception {
    public NoEventException(String message) {
        super(message);
    }
}
