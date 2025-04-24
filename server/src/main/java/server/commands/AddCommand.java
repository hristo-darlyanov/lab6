package server.commands;

import common.model.Movie;
import common.requests.AddRequest;
import common.requests.Request;
import common.responses.AddResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class AddCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        AddRequest addRequest = (AddRequest) request;
        Movie movie = addRequest.getMovie();
        collectionManager.getCollection().add(movie);

        AddResponse addResponse = new AddResponse("Element added successfully.");
        addResponse.setRequestId(request.getRequestId());
        return addResponse;
    }

    @Override
    public String getInfo() {
        return "add a new element to the collection";
    }
}
