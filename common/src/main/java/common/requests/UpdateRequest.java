package common.requests;

import common.commands.CommandTrigger;
import common.model.Movie;

public class UpdateRequest extends Request {
    private final int id;
    private final Movie movie;

    public UpdateRequest(int id, Movie movie) {
        super(CommandTrigger.UPDATE);
        this.id = id;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }
}
