package common.responses;

import common.commands.CommandTrigger;
import common.model.Movie;

import java.util.ArrayDeque;

public class FilterContainsNameResponse extends Response{
    private final ArrayDeque<Movie> movies;

    public FilterContainsNameResponse(ArrayDeque<Movie> movies) {
        super(CommandTrigger.FILTER_CONTAINS_NAME);
        this.movies = movies;
    }

    public ArrayDeque<Movie> getMovies() {
        return movies;
    }
}
