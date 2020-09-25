package exception;

/**
 * Thrown when the user tries to input an id that already is taken.
 */
public class DuplicateIDException extends ServiceException {
    public DuplicateIDException(String message) {
        super(message);
    }
}
