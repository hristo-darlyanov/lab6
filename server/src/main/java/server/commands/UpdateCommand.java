package server.commands;

import common.model.Movie;
import common.requests.Request;
import common.requests.UpdateRequest;
import common.responses.Response;
import common.responses.UpdateResponse;
import server.managers.CollectionManager;

public class UpdateCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        UpdateRequest updateRequest = (UpdateRequest) request;
        int id = updateRequest.getId();
        Movie updateMovie = updateRequest.getMovie();

        try {
            collectionManager.getCollection().removeIf(movie -> movie.getId() == id);
            collectionManager.add(updateMovie);
        } catch (Exception e) {
            UpdateResponse updateResponse = new UpdateResponse(null);
            updateResponse.setRequestId(request.getRequestId());
            updateResponse.setError(e.getMessage());
            return updateResponse;
        }

        UpdateResponse updateResponse = new UpdateResponse("Element updated successfully.");
        updateResponse.setRequestId(request.getRequestId());
        return updateResponse;
    }

    @Override
    public String getInfo() {
        return "updates the value of a collection element whose ID is equal to the given one";
    }
}
