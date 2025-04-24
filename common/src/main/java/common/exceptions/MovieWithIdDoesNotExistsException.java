package common.exceptions;

public class MovieWithIdDoesNotExistsException extends RuntimeException {
    public MovieWithIdDoesNotExistsException(String id) {
        super(String.format("Movie with ID %s does not exist", id));
    }

    public MovieWithIdDoesNotExistsException(String id, Throwable cause) {
        super(String.format("Movie with ID %s does not exist", id), cause);
    }
}
