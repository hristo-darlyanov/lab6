package common.requests;

import common.commands.CommandTrigger;
import common.model.Movie;

public class AddRequest extends Request {
    private final Movie movie;

    public AddRequest(Movie movie) {
        super(CommandTrigger.ADD);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
