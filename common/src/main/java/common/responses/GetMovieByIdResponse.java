package common.responses;

import common.commands.CommandTrigger;
import common.model.Movie;

public class GetMovieByIdResponse extends Response {
    private final Movie movie;

    public GetMovieByIdResponse(Movie movie) {
        super(CommandTrigger.GET_MOVIE_BY_ID);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
