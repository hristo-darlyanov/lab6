package common.model;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

/**
 * Movie genre
 */
@XmlType(name = "MovieGenre")
@XmlEnum
public enum MovieGenre implements Serializable {
    @XmlEnumValue("WESTERN")
    WESTERN,
    @XmlEnumValue("DRAMA")
    DRAMA,
    @XmlEnumValue("COMEDY")
    COMEDY,
    @XmlEnumValue("MUSICAL")
    MUSICAL,
    @XmlEnumValue("ADVENTURE")
    ADVENTURE,
    @XmlEnumValue("SCI_FI")
    SCI_FI;

    /**
     * Checks if the genre exists
     * @param genre Genre to check
     * @return True if the genre exists, false otherwise
     */
    public static boolean contains(String genre) {
        for (MovieGenre g : MovieGenre.values()) {
            if (g.name().equals(genre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all genres
     * @return All genres
     */
    public static String getGenres() {
        StringBuilder genres = new StringBuilder();
        for (MovieGenre g : MovieGenre.values()) {
            genres.append(g.name()).append(", ");
        }
        return genres.substring(0, genres.length() - 2);
    }

    public static MovieGenre validateMovieGenre(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        try {
            return MovieGenre.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DRAMA;
        }
    }
}