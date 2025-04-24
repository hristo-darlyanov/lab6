package server.utilities;

import common.exceptions.MovieWithIdDoesNotExistsException;
import common.utilities.Validator;
import server.managers.CollectionManager;

public class ServerValidator extends Validator {
    public static boolean movieWithIdExists(CollectionManager collectionManager, String argument) {
        int id = parseAndValidateInt(argument);
        if (collectionManager.getCollection().stream().noneMatch(movie -> movie.getId() == id)) {
            throw new MovieWithIdDoesNotExistsException(String.valueOf(id));
        }
        return true;
    }
}
