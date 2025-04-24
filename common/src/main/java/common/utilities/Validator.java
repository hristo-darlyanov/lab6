package common.utilities;

import common.exceptions.IncorrectIdException;

public class Validator {
    public static int parseAndValidateInt(String idString) {
        try {
            if (idString == null || idString.isEmpty()) {
                throw new IncorrectIdException(idString);
            }
            int id = Integer.parseInt(idString);
            return id;
        } catch (NumberFormatException e) {
            throw new IncorrectIdException(idString, e);
        }
    }
}
