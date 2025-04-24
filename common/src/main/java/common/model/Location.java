package common.model;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

/**
 * Location class
 * Contains coordinates
 */
@XmlRootElement(name = "Location")
@XmlType(propOrder = { "x", "y", "z" })
public class Location implements Serializable {
    private Float x;
    private float y;
    private float z;

    public Location() {}

    /**
     * @return X coordinate
     */
    @XmlElement
    public Float getX() {
        return x;
    }

    /**
     * @param x X coordinate
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * @return Y coordinate
     */
    @XmlElement
    public float getY() {
        return y;
    }

    /**
     * @param y Y coordinate
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return Z coordinate
     */
    @XmlElement
    public float getZ() {
        return z;
    }

    /**
     * @param z Z coordinate
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * @return Info about location
     */
    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + ", z=" + z + "}";
    }

    
    /** 
     * @param location
     */
    public static void validateLocation(Location location) {
        if (location == null) {
            System.out.println("Location cannot be null.");
            return;
        }
        // x must not be null.
        if (location.getX() == null) {
            location.setX(0.0f);
        }
        // y and z are primitives so they are always set.
    }
}