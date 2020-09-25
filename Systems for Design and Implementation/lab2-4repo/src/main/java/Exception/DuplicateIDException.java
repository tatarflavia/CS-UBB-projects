package Exception;

/**
 * Thrown when the user tries to input an id that already is taken.
 */
public class DuplicateIDException extends RejectedInputException {
    public DuplicateIDException(String message) {
        super(message);
    }
    public DuplicateIDException() {
        super("ID is already in use.");
    }
}
