package core.exception;

/**
 * Thrown when the user tries to input an id that already is taken.
 */
public class IDException extends ServiceException {
    public IDException(String message) {
        super(message);
    }
    public IDException() {
        super("ID is already in use.");
    }
}
