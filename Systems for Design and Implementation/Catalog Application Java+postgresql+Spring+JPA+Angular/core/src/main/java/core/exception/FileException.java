package core.exception;

/**
 * Thrown when the file is badly written or is not in the project folder.
 */
public class FileException extends ServiceException {
    public FileException(String message) {
        super(message);
    }
}
