package server.commands;


import common.model.Movie;
import common.requests.AddIfMinRequest;
import common.requests.Request;
import common.responses.AddIfMinResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class AddIfMinCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        AddIfMinRequest addIfMinRequest = (AddIfMinRequest) request;
        Movie movie = addIfMinRequest.getMovie();

        if (collectionManager.getCollection().isEmpty() || movie.compareTo(collectionManager.getCollection().stream().min(Movie::compareTo).orElse(null)) < 0) {
            collectionManager.add(movie);
            AddIfMinResponse addIfMinResponse = new AddIfMinResponse("Element added successfully.");
            addIfMinResponse.setRequestId(request.getRequestId());
            return addIfMinResponse;
        } else {
            AddIfMinResponse addIfMinResponse = new AddIfMinResponse("Element not added. It is not less than the minimum element in the collection.");
            addIfMinResponse.setRequestId(request.getRequestId());
            return addIfMinResponse;
        }
    }

    @Override
    public String getInfo() {
        return "adds a new element to the collection if it is less than the minimum element in the collection";
    }
}
