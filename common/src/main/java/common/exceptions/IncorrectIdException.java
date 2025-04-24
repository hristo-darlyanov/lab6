package common.exceptions;

public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException(String id) {
        super(String.format("Incorrect Movie ID: %s", id));
    }

    public IncorrectIdException(String id, Throwable cause) {
        super(String.format("Incorrect Movie ID: %s", id), cause);
    }
}
