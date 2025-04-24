package common.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Coordinates class
 */

@XmlRootElement(name = "Coordinates")
@XmlType(propOrder = { "x", "y" })
public class Coordinates implements Serializable {
    private Integer x;
    private Float y;

    public Coordinates() {
    }

    /**
     */
    @XmlElement
    public Integer getX() {
        return x;
    }

    /**
     * @param x X coordinate
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * @return Y coordinate
     */
    @XmlElement
    public Float getY() {
        return y;
    }

    /**
     * @param y Y coordinate
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * @return Info about coordinates
     */
    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }

    
    /** 
     *
     */
    public static void validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println("Coordinates cannot be null.");
            return;
        }
        // x: cannot be null and must be greater than -500.
        if (coordinates.getX() == null || coordinates.getX() <= -500) {
            coordinates.setX(0);  // default x value
        }
        // y: cannot be null.
        if (coordinates.getY() == null) {
            coordinates.setY(0.0f);  // default y value
        }
    }
}
