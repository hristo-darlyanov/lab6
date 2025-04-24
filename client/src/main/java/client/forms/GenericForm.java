package client.forms;

import client.utilities.Console;
import common.model.MovieGenre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * GenericForm provides common prompting and parsing methods for user input.
 */
public class GenericForm {
    
    /** 
     * @param prompt
     * @return String
     */
    public static String promptReadString(String prompt) {
        return promptReadString(prompt, false);
    }

    public static String promptReadString(String prompt, boolean canBeNull) {
        String text = Console.promptReadLine(prompt);
        if (text.isEmpty() && !canBeNull) {
            System.out.println("Input cannot be empty. Please try again.");
            return promptReadString(prompt, canBeNull);
        }
        return text;
    }

    public static int promptReadPositiveInt(String prompt) {
        return promptReadPositiveInt(prompt, false);
    }

    public static int promptReadPositiveInt(String prompt, boolean canBeNull) {
        while (true) {
            String input = Console.promptReadLine(prompt);
            if (input.isEmpty() && canBeNull) {
                return -1;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Number must be positive. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    public static float promptReadFloat(String prompt) {
        return promptReadFloat(prompt, false);
    }

    public static float promptReadFloat(String prompt, boolean canBeNull) {
        while (true) {
            String input = Console.promptReadLine(prompt);
            if (input.isEmpty() && canBeNull) {
                return -1;
            }
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid float. Please try again.");
            }
        }
    }

    public static double promptReadDouble(String prompt) {
        return promptReadDouble(prompt, false);
    }

    public static double promptReadDouble(String prompt, boolean canBeNull) {
        while (true) {
            String input = Console.promptReadLine(prompt);
            if (input.isEmpty() && canBeNull) {
                return -1;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid double. Please try again.");
            }
        }
    }

    /**
     * Ask for a date
     * @param prompt Prompt to display
     * @return LocalDate input
     */
    public static LocalDate askBirthday(String prompt) {
        return askBirthday(prompt, false);
    }

    /**
     * Ask for a date
     * @param prompt Prompt to display
     * @param canBeNull Whether the input can be null
     * @return LocalDate input
     */
    public static LocalDate askBirthday(String prompt, boolean canBeNull) {
        while (true) {
            String input = Console.promptReadLine(prompt);
            if (input.isEmpty() && canBeNull) {
                return null;
            }
            try {
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
            }
        }
    }

    /**
     * Ask for an enum
     * @param prompt Prompt to display
     * @return Enum input
     */
    public static MovieGenre askEnum(String prompt) {
        return askEnum(prompt, false);
    }

    /**
     * Ask for an enum
     * @param prompt Prompt to display
     * @param canBeNull Whether the input can be null
     * @return Enum input
     */
    public static MovieGenre askEnum(String prompt, boolean canBeNull) {
        while (true) {
            System.out.print("Available options: ");
            MovieGenre[] constants = MovieGenre.values(); 
            for (int i = 0; i < constants.length; i++) {
                System.out.print(constants[i].name());
                if (i < constants.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            String input = Console.promptReadLine(prompt);
            if (input.isEmpty() && canBeNull) {
                return null;
            }
            try {
                return MovieGenre.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option. Please enter one of the listed options.");
            }
        }
    }
}
