package common.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Movies wrapper
 */
@XmlRootElement(name="Movies")
public class MoviesWrapper {    
    private List<Movie> movies;

    /**
     * @return List of movies
     */
    @XmlElement(name="Movie")
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * @param movies List of movies
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
