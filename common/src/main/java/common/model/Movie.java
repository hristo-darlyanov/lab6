package common.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;


/**
 * Movie class
 * Contains information about a movie
 */
@XmlRootElement(name = "Movie")
@XmlType(propOrder = { "id", "name", "coordinates", "creationDate", "oscarsCount", "goldenPalmCount", "totalBoxOffice", "genre", "screenwriter" })
public class Movie implements Comparable<Movie>, Serializable {
    private static final Random random = new Random();

    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private long oscarsCount;
    private Integer goldenPalmCount;
    private int totalBoxOffice;
    private MovieGenre genre;
    private Person screenwriter;

    public Movie() {
        generateID();
        this.creationDate = LocalDate.now();
    }

    /**
     * @return ID
     */
    @XmlElement
    public Long getId() {
        return id;
    }

    /**
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void generateID() {
        this.id = (long) (Math.abs(random.nextInt(Integer.MAX_VALUE)) + 1);
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
     * @return Coordinates
     */
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates Coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return Creation date
     */
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate Creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return Oscars count
     */
    @XmlElement
    public long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * @param oscarsCount Oscars count
     */
    public void setOscarsCount(long oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    /**
     * @return Golden Palm count
     */
    @XmlElement
    public Integer getGoldenPalmCount() {
        return goldenPalmCount;
    }

    /**
     * @param goldenPalmCount Golden Palm count
     */
    public void setGoldenPalmCount(Integer goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    /**
     * @return Total box office
     */
    @XmlElement
    public int getTotalBoxOffice() {
        return totalBoxOffice;
    }

    /**
     * @param totalBoxOffice Total box office
     */
    public void setTotalBoxOffice(int totalBoxOffice) {
        this.totalBoxOffice = totalBoxOffice;
    }

    /**
     * @return Genre
     */
    @XmlElement
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * @param genre Genre
     */
    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * @return Screenwriter
     */
    @XmlElement
    public Person getScreenwriter() {
        return screenwriter;
    }

    /**
     * @param screenwriter Screenwriter
     */
    public void setScreenwriter(Person screenwriter) {
        this.screenwriter = screenwriter;
    }

    /**
     * @return Info about movie
     */
    @Override
    public String toString() {
        return "Movie {" +
                "\n  id: " + id +
                ",\n  name: '" + name + '\'' +
                ",\n  coordinates: " + coordinates +
                ",\n  creationDate: " + creationDate +
                ",\n  oscarsCount: " + oscarsCount +
                ",\n  goldenPalmCount: " + (goldenPalmCount != null ? goldenPalmCount : "N/A") +
                ",\n  totalBoxOffice: " + totalBoxOffice +
                ",\n  genre: " + (genre != null ? genre : "N/A") +
                ",\n  screenwriter: " + (screenwriter != null ? screenwriter : "N/A") +
                "\n}";
    }

    /**
     * @param m Object to compare
     * @return Comparison result
     */
    @Override
    public int compareTo(Movie m) {
        return this.name.length() - m.name.length();
    }

    
    /** 
     * @param movie
     */
    public static void validateMovie(Movie movie) {
        if (movie == null) {
            System.out.println("Movie cannot be null.");
            return;
        }
        // Validate id: must not be null and > 0, otherwise generate a new one.
        if (movie.getId() == null || movie.getId() <= 0) {
            movie.generateID();
        }
    
        // Validate name: cannot be null or empty.
        if (movie.getName() == null || movie.getName().trim().isEmpty()) {
            movie.setName("To be declared");
        }
    
        // Validate coordinates: cannot be null.
        if (movie.getCoordinates() == null) {
            Coordinates defaultCoords = new Coordinates();
            defaultCoords.setX(0);
            defaultCoords.setY(0.0f);
            movie.setCoordinates(defaultCoords);
        } else {
            Coordinates.validateCoordinates(movie.getCoordinates());
        }
    
        // Validate creation date: cannot be null.
        if (movie.getCreationDate() == null) {
            movie.setCreationDate(LocalDate.now());
        }
    
        // Validate oscarsCount: must be greater than 0.
        if (movie.getOscarsCount() <= 0) {
            movie.setOscarsCount(1);
        }
    
        // Validate goldenPalmCount: if not null then must be greater than 0.
        if (movie.getGoldenPalmCount() != null && movie.getGoldenPalmCount() <= 0) {
            movie.setGoldenPalmCount(1);
        }
    
        // Validate totalBoxOffice: must be greater than 0.
        if (movie.getTotalBoxOffice() <= 0) {
            movie.setTotalBoxOffice(1);
        }
    
        // Validate MovieGenre: if non-null, check that its value exists; if not, default to DRAMA.
        if (movie.getGenre() != null) {
            boolean valid = false;
            for (MovieGenre mg : MovieGenre.values()) {
                if (mg.equals(movie.getGenre())) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                movie.setGenre(MovieGenre.DRAMA);
            }
        }
    
        // Validate screenwriter: if null, assign a default Person.
        if (movie.getScreenwriter() == null) {
            Person defaultPerson = new Person();
            defaultPerson.setName("Default screenwriter");
            movie.setScreenwriter(defaultPerson);
        } else {
            Person.validatePerson(movie.getScreenwriter());
        }
    }
}
