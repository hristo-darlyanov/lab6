package client.forms;

import common.model.Movie;

/**
 * Form for creating a new Movie.
 * Prompts the user for movie details and constructs a Movie instance.
 */
public class MovieForm extends GenericForm {
    
    /** 
     * @return Movie
     */
    public static Movie ask() {
        Movie movie = new Movie();
        movie.setName(promptReadString("Enter movie name: "));
        movie.setCoordinates(CoordinatesForm.ask());
        movie.setOscarsCount(promptReadPositiveInt("Enter oscars count (positive integer): "));
        movie.setGoldenPalmCount(promptReadPositiveInt("Enter golden palm count (positive integer, empty for null): ", true));
        movie.setTotalBoxOffice(promptReadPositiveInt("Enter total box office (positive integer): "));
        movie.setGenre(askEnum("Enter movie genre (empty for null): ", true));
        movie.setScreenwriter(PersonForm.ask());
        return movie;
    }
}
