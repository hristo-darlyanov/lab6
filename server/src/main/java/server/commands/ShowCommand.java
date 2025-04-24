package server.commands;

import common.model.Movie;
import common.requests.Request;
import common.responses.Response;
import common.responses.ShowResponse;
import server.managers.CollectionManager;

import java.util.ArrayDeque;

public class ShowCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        ArrayDeque<Movie> movies = collectionManager.getCollection();
        ShowResponse showResponse = new ShowResponse(movies);
        showResponse.setRequestId(request.getRequestId());
        return showResponse;
    }

    @Override
    public String getInfo() {
        return "show all elements of the collection";
    }
}
