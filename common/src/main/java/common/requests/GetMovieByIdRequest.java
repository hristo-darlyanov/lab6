package common.requests;

import common.commands.CommandTrigger;

public class GetMovieByIdRequest extends Request {
    private final int id;

    public GetMovieByIdRequest(int id) {
        super(CommandTrigger.GET_MOVIE_BY_ID);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
