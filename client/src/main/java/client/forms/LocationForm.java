package client.forms;

import common.model.Location;

/**
 * Form for creating a new Location.
 * Prompts the user for location coordinates.
 */
public class LocationForm extends GenericForm {
    
    /** 
     * @return Location
     */
    public static Location ask() {
        Location location = new Location();
        location.setX(promptReadFloat("Enter location x (float): "));
        location.setY(promptReadFloat("Enter location y (float): "));
        location.setZ(promptReadFloat("Enter location z (float): "));
        return location;
    }
}
