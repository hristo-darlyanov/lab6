package common.requests;

import common.commands.CommandTrigger;
import common.model.Movie;

public class AddIfMinRequest extends Request {
    private final Movie movie;

    public AddIfMinRequest(Movie movie) {
        super(CommandTrigger.ADD_IF_MIN);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
