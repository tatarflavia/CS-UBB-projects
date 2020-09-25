package Exception;

/**
 * Thrown when the file is badly written or is not in the project folder.
 */
public class FileException extends RejectedInputException {
    public FileException(String message) {
        super(message);
    }
}
