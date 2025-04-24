package server.commands;

import common.model.Movie;
import common.model.MovieGenre;
import common.requests.CountByGenreRequest;
import common.requests.Request;
import common.responses.CountByGenreResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class CountByGenreCommand implements ServerCommand {
    private final CollectionManager collectionManager;
    public CountByGenreCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        CountByGenreRequest countByGenreRequest = (CountByGenreRequest) request;
        MovieGenre genre = countByGenreRequest.getGenre();
        int count = 0;
        for (Movie movie : collectionManager.getCollection()) {
            if (movie.getGenre() == genre) {
                count++;
            }
        }

        CountByGenreResponse countByGenreResponse = new CountByGenreResponse(count);
        countByGenreResponse.setRequestId(request.getRequestId());
        return countByGenreResponse;
    }

    @Override
    public String getInfo() {
        return "counts the number of elements in the collection with a given genre.";
    }
}
