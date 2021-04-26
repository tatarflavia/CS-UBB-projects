package Exception;


public class NoGradeException extends RejectedInputException {
    public NoGradeException(String message) {
        super(message);
    }
}