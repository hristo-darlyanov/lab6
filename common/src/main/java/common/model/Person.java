package common.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Person class
 * Contains information about a person
 */
@XmlRootElement(name = "Person")
@XmlType(propOrder = { "name", "birthday", "weight", "passportID", "location" })
public class Person implements Serializable {
    private String name;
    private LocalDate birthday;
    private double weight;
    private String passportID;
    private Location location;

    public Person() {
    }

    /**
     * @return Name
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Birthday
     */
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday Birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * @return Weight
     */
    @XmlElement
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight Weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return Passport ID
     */
    @XmlElement
    public String getPassportID() {
        return passportID;
    }

    /**
     * @param passportID Passport ID
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * @return Location
     */
    @XmlElement
    public Location getLocation() {
        return location;
    }

    /**
     * @param location Location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return Info about person
     */
    @Override
    public String toString() {
        return "{name: '" + name + '\'' +
                ", birthday: " + birthday +
                ", weight: " + weight +
                ", passportID: " + (passportID != null ? passportID : "N/A") +
                ", location: " + (location != null ? location : "N/A") +
                "}";
    }

    
    /** 
     * @param person
     */
    public static void validatePerson(Person person) {
        if (person == null) {
            System.out.println("Person cannot be null.");
            return;
        }
        // Validate name: non-null and non-empty.
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            person.setName("Default Person");
        }
        // Validate birthday: cannot be null.
        if (person.getBirthday() == null) {
            person.setBirthday(LocalDate.now());
        }
        // Validate weight: must be > 0.
        if (person.getWeight() <= 0) {
            person.setWeight(1.0);
        }
        // Validate passportID: if not null, length must be at least 4.
        if (person.getPassportID() != null && person.getPassportID().length() < 4) {
            person.setPassportID("XXXX");
        }
        // Validate location if provided.
        if (person.getLocation() != null) {
            Location.validateLocation(person.getLocation());
        }
    }
}
