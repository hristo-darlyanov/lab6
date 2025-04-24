package server.commands;

import common.commands.Command;
import common.model.Movie;
import common.requests.FilterContainsNameRequest;
import common.requests.Request;
import common.responses.FilterContainsNameResponse;
import common.responses.Response;
import server.managers.CollectionManager;

import java.util.ArrayDeque;

public class FilterContainsNameCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        FilterContainsNameRequest requestFilter = (FilterContainsNameRequest) request;
        String name = requestFilter.getName();

        ArrayDeque<Movie> movies = collectionManager.getCollection().stream()
                .filter(movie -> movie.getName().contains(name))
                .collect(ArrayDeque::new, ArrayDeque::add, ArrayDeque::addAll);

        FilterContainsNameResponse response = new FilterContainsNameResponse(movies);
        response.setRequestId(request.getRequestId());
        return response;
    }

    @Override
    public String getInfo() {
        return "filters the collection by name";
    }
}
