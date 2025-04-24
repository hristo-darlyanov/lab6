package common.responses;

import common.commands.CommandTrigger;
import common.model.Movie;

import java.util.ArrayDeque;

public class ShowResponse extends Response {
    private final ArrayDeque<Movie> movies;

    public ShowResponse(ArrayDeque<Movie> movies) {
        super(CommandTrigger.SHOW);
        this.movies = movies;
    }

    public ArrayDeque<Movie> getMovies() {
        return movies;
    }
}
