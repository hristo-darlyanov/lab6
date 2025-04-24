package common.requests;

import common.commands.CommandTrigger;
import common.model.MovieGenre;

public class CountByGenreRequest extends Request {
    private final MovieGenre genre;
    public CountByGenreRequest(MovieGenre genre) {
        super(CommandTrigger.COUNT_BY_GENRE);
        this.genre = genre;
    }

    public MovieGenre getGenre() {
        return genre;
    }
}
