package client.forms;

import client.utilities.Console;
import common.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Form for updating an existing movie.
 * Prompts the user to enter new values for movie fields 
 * (empty input leaves the field unchanged).
 */
public class UpdateForm extends GenericForm {
    
    /** 
     * @param movie
     * @return Movie
     */
    public static Movie update(Movie movie) {
        System.out.println("Updating movie. Press ENTER to leave a field unchanged.");

        // Update name
        String nameInput = Console.promptReadLine("Enter movie name [" + movie.getName() + "]: ");
        if (!nameInput.trim().isEmpty()) {
            movie.setName(nameInput);
        }

        // Update coordinates
        Coordinates coords = movie.getCoordinates();
        if (coords == null) {
            System.out.println("Movie has no coordinates. Creating new coordinates.");
            movie.setCoordinates(CoordinatesForm.ask());
        } else {
            String xInput = Console.promptReadLine("Enter coordinate x [" + coords.getX() + "]: ");
            if (!xInput.trim().isEmpty()) {
                try {
                    int x = Integer.parseInt(xInput);
                    if (x > -500) {
                        coords.setX(x);
                    } else {
                        System.out.println("x must be greater than -500. Keeping previous value.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer. Keeping previous value.");
                }
            }
            String yInput = Console.promptReadLine("Enter coordinate y [" + coords.getY() + "]: ");
            if (!yInput.trim().isEmpty()) {
                try {
                    float y = Float.parseFloat(yInput);
                    coords.setY(y);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid float. Keeping previous value.");
                }
            }
        }

        // Update oscars count
        String oscarsInput = Console.promptReadLine("Enter oscars count (" + movie.getOscarsCount() + "): ");
        if (!oscarsInput.trim().isEmpty()) {
            try {
                int oscars = Integer.parseInt(oscarsInput);
                if (oscars >= 0) {
                    movie.setOscarsCount(oscars);
                } else {
                    System.out.println("Oscars count cannot be negative. Keeping previous value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Keeping previous value.");
            }
        }

        // Update golden palm count (can be null)
        String goldenPalmInput = Console.promptReadLine("Enter golden palm count [" 
                + (movie.getGoldenPalmCount() != null ? movie.getGoldenPalmCount() : "null") + "]: ");
        if (!goldenPalmInput.trim().isEmpty()) {
            try {
                int goldenPalm = Integer.parseInt(goldenPalmInput);
                if (goldenPalm >= 0) {
                    movie.setGoldenPalmCount(goldenPalm);
                } else {
                    System.out.println("Golden palm count cannot be negative. Keeping previous value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Keeping previous value.");
            }
        }

        // Update total box office
        String boxOfficeInput = Console.promptReadLine("Enter total box office (" + movie.getTotalBoxOffice() + "): ");
        if (!boxOfficeInput.trim().isEmpty()) {
            try {
                int boxOffice = Integer.parseInt(boxOfficeInput);
                if (boxOffice >= 0) {
                    movie.setTotalBoxOffice(boxOffice);
                } else {
                    System.out.println("Total box office cannot be negative. Keeping previous value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Keeping previous value.");
            }
        }

        // Update genre
        String genreInput = Console.promptReadLine("Enter movie genre [" 
                + (movie.getGenre() != null ? movie.getGenre() : "null") + "]: ");
        if (!genreInput.trim().isEmpty()) {
            try {
                movie.setGenre(MovieGenre.valueOf(genreInput.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre. Keeping previous value.");
            }
        }

        // Update screenwriter
        Person person = movie.getScreenwriter();
        if (person == null) {
            System.out.println("Movie has no screenwriter. Creating new one.");
            movie.setScreenwriter(PersonForm.ask());
        } else {
            String personName = Console.promptReadLine("Enter screenwriter name [" + person.getName() + "]: ");
            if (!personName.trim().isEmpty()) {
                person.setName(personName);
            }
            String birthdayInput = Console.promptReadLine("Enter screenwriter birthday (YYYY-MM-DD) [" 
                    + (person.getBirthday() != null ? person.getBirthday() : "null") + "]: ");
            if (!birthdayInput.trim().isEmpty()) {
                try {
                    LocalDate birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ISO_LOCAL_DATE);
                    person.setBirthday(birthday);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Keeping previous value.");
                }
            }
            String weightInput = Console.promptReadLine("Enter screenwriter weight (" + person.getWeight() + "): ");
            if (!weightInput.trim().isEmpty()) {
                try {
                    double weight = Double.parseDouble(weightInput);
                    if (weight > 0) {
                        person.setWeight(weight);
                    } else {
                        System.out.println("Weight must be greater than 0. Keeping previous value.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Keeping previous value.");
                }
            }
            String passportInput = Console.promptReadLine("Enter passport ID [" 
                    + (person.getPassportID() != null ? person.getPassportID() : "null") + "]: ");
            if (!passportInput.trim().isEmpty()) {
                if (passportInput.length() >= 4) {
                    person.setPassportID(passportInput);
                } else {
                    System.out.println("Passport ID must be at least 4 characters. Keeping previous value.");
                }
            }
            String locChoice = Console.promptReadLine("Do you want to update location? (yes/no): ");
            if (locChoice.equalsIgnoreCase("yes") || locChoice.equalsIgnoreCase("y")) {
                Location location = person.getLocation();
                if (location == null) {
                    location = LocationForm.ask();
                    person.setLocation(location);
                } else {
                    String locX = Console.promptReadLine("Enter location x [" + location.getX() + "]: ");
                    if (!locX.trim().isEmpty()) {
                        try {
                            float lx = Float.parseFloat(locX);
                            location.setX(lx);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Keeping previous value.");
                        }
                    }
                    String locY = Console.promptReadLine("Enter location y [" + location.getY() + "]: ");
                    if (!locY.trim().isEmpty()) {
                        try {
                            float ly = Float.parseFloat(locY);
                            location.setY(ly);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Keeping previous value.");
                        }
                    }
                    String locZ = Console.promptReadLine("Enter location z [" + location.getZ() + "]: ");
                    if (!locZ.trim().isEmpty()) {
                        try {
                            float lz = Float.parseFloat(locZ);
                            location.setZ(lz);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Keeping previous value.");
                        }
                    }
                }
            }
        }
        return movie;
    }
}
