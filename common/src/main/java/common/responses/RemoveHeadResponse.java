package common.responses;

import common.commands.CommandTrigger;
import common.model.Movie;

public class RemoveHeadResponse extends Response {
    private final Movie movie;

    public RemoveHeadResponse(Movie movie) {
        super(CommandTrigger.REMOVE_HEAD);
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
