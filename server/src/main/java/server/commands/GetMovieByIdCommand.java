package server.commands;

import common.commands.Command;
import common.requests.GetMovieByIdRequest;
import common.requests.Request;
import common.responses.GetMovieByIdResponse;
import common.responses.Response;
import server.managers.CollectionManager;
import server.utilities.ServerValidator;

public class GetMovieByIdCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public GetMovieByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        GetMovieByIdRequest getMovieByIdRequest = (GetMovieByIdRequest) request;
        int id = getMovieByIdRequest.getId();

        try {
            ServerValidator.movieWithIdExists(collectionManager, String.valueOf(id));
        } catch (Exception e) {
            GetMovieByIdResponse getMovieByIdResponse = new GetMovieByIdResponse(null);
            getMovieByIdResponse.setRequestId(request.getRequestId());
            getMovieByIdResponse.setError(e.getMessage());
            return getMovieByIdResponse;
        }

        GetMovieByIdResponse getMovieByIdResponse = new GetMovieByIdResponse(collectionManager.getCollection().stream().filter(movie -> movie.getId() == id).findFirst().orElse(null));
        getMovieByIdResponse.setRequestId(request.getRequestId());
        return getMovieByIdResponse;
    }

    @Override
    public String getInfo() {
        return "retrieves a movie by its ID";
    }
}
