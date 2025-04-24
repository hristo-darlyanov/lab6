package server.commands;

import common.model.Movie;
import common.requests.RemoveLowerRequest;
import common.requests.Request;
import common.responses.RemoveLowerResponse;
import common.responses.Response;
import server.managers.CollectionManager;
import server.utilities.ServerValidator;

public class RemoveLowerCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        RemoveLowerRequest removeLowerRequest = (RemoveLowerRequest) request;
        int id = removeLowerRequest.getId();

        try {
            ServerValidator.movieWithIdExists(collectionManager, String.valueOf(id));
        } catch (Exception e) {
            RemoveLowerResponse removeLowerResponse = new RemoveLowerResponse(null);
            removeLowerResponse.setRequestId(request.getRequestId());
            removeLowerResponse.setError(e.getMessage());
            return removeLowerResponse;
        }

        Movie movieToRemove = collectionManager.getCollection().stream().filter(movie -> movie.getId() == id).findFirst().get();
        collectionManager.getCollection().removeIf(movie -> movie.compareTo(movieToRemove) < 0);

        RemoveLowerResponse removeLowerResponse = new RemoveLowerResponse("Removed all elements lower than the given element.");
        removeLowerResponse.setRequestId(request.getRequestId());
        return removeLowerResponse;
    }

    @Override
    public String getInfo() {
        return "removes all elements from the collection that are less than the given element";
    }
}
