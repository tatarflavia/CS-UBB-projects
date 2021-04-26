package core.exception;

/**
 * Thrown when one of the fields is invalid as is, without even comparing it to other data.
 */
public class ValidatorException extends ServiceException {
    public ValidatorException(String message) {
        super(message);
}
}
