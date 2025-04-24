package server.commands;

import common.model.Movie;
import common.requests.Request;
import common.responses.RemoveHeadResponse;
import common.responses.Response;
import server.managers.CollectionManager;

import java.util.NoSuchElementException;

public class RemoveHeadCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        RemoveHeadResponse response;

        try {
            Movie movie = collectionManager.getCollection().removeFirst();
            response = new RemoveHeadResponse(movie);
            response.setRequestId(request.getRequestId());
            return response;
        } catch (NoSuchElementException e) {
            response = new RemoveHeadResponse(null);
            response.setRequestId(request.getRequestId());
            response.setError("Collection is empty");
            return response;
        }
    }

    @Override
    public String getInfo() {
        return "removes the first element from the collection";
    }
}
