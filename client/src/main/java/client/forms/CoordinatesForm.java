package client.forms;

import client.utilities.Console;
import common.model.Coordinates;

/**
 * Form for building a Coordinates object by reading user input.
 */
public class CoordinatesForm extends GenericForm{
    
    /** 
     * @return Coordinates
     */
    public static Coordinates ask() {
        Coordinates coordinates = new Coordinates();
        while (true) {
            String input = Console.promptReadLine("Enter coordinates x (integer, greater than -500): ");
            if (input.isEmpty()) {
                System.out.println("Coordinates x cannot be empty.");
                continue;
            }
            try {
                int x = Integer.parseInt(input);
                if (x > -500) {
                    coordinates.setX(x);
                    break;
                } else {
                    System.out.println("x must be greater than -500.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer for x. Please try again.");
            }
        }
        coordinates.setY(promptReadFloat("Enter coordinates y (float): "));
        return coordinates;
    }
}
