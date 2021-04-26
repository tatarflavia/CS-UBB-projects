package core.exception;

/**
 * Thrown when the input provided by the user is invalid and will be rejected.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
