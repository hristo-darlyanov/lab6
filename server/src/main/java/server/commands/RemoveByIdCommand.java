package server.commands;

import common.requests.RemoveByIdRequest;
import common.requests.Request;
import common.responses.RemoveByIdResponse;
import common.responses.Response;
import server.managers.CollectionManager;
import server.utilities.ServerValidator;

public class RemoveByIdCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        RemoveByIdRequest removeByIdRequest = (RemoveByIdRequest) request;
        int id = removeByIdRequest.getId();

        try {
            ServerValidator.movieWithIdExists(collectionManager, String.valueOf(id));
        } catch (Exception e) {
            RemoveByIdResponse removeByIdResponse = new RemoveByIdResponse(null);
            removeByIdResponse.setRequestId(request.getRequestId());
            removeByIdResponse.setError(e.getMessage());
            return removeByIdResponse;
        }

        collectionManager.getCollection().removeIf(m -> m.getId() == id);

        RemoveByIdResponse removeByIdResponse = new RemoveByIdResponse("Element with ID " + id + " removed successfully.");
        removeByIdResponse.setRequestId(request.getRequestId());
        return removeByIdResponse;
    }

    @Override
    public String getInfo() {
        return "removes an element from the collection by its ID";
    }
}
