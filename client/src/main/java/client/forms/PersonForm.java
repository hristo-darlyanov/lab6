package client.forms;

import common.model.Person;

/**
 * Form for creating a new Person.
 * Prompts the user for all Person fields and validates input.
 */
public class PersonForm extends GenericForm {
    
    /** 
     * @return Person
     */
    public static Person ask() {
        Person person = new Person();
        person.setName(promptReadString("Enter person name: "));
        person.setBirthday(askBirthday("Enter birthday (YYYY-MM-DD format): "));
        while (true) {
            double weight = promptReadDouble("Enter weight (double, greater than 0): ");
            if (weight > 0) {
                person.setWeight(weight);
                break;
            } else {
                System.out.println("Weight must be greater than 0.");
            }
        }
        while (true) {
            String passportID = promptReadString("Enter passport ID (at least 4 characters, empty for null): ", true);
            if (passportID != null && passportID.length() >= 4) {
                person.setPassportID(String.valueOf(passportID));
                break;
            } else {
                if (passportID == null || passportID.isEmpty()) {
                    person.setPassportID(null);
                    break;
                } else {
                    System.out.println("Passport ID must be at least 4 characters.");
                }
            }
        }
        String locChoice = promptReadString("Do you want to enter location? (yes/no): ");
        if (locChoice.equals("yes") || locChoice.equals("y")) {
            person.setLocation(LocationForm.ask());
        } else {
            person.setLocation(null);
        }
        return person;
    }
}
