package Exception;

/**
 * Thrown when the input provided by the user is invalid and will be rejected.
 */
public class RejectedInputException extends RuntimeException {
    public RejectedInputException(String message) {
        super(message);
    }
}
