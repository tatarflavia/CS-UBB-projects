package exception;

import java.io.Serializable;

/**
 * Thrown when the input provided by the user is invalid and will be rejected.
 */
public class ServiceException extends RuntimeException implements Serializable {
    public ServiceException(String message) {
        super(message);
    }
}
