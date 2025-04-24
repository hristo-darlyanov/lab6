package server.commands;

import common.model.Movie;
import common.requests.CountLessThanOscarsRequest;
import common.requests.Request;
import common.responses.CountLessThanOscarsResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class CountLessThanOscarsCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public CountLessThanOscarsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        CountLessThanOscarsRequest countLessThanOscarsRequest = (CountLessThanOscarsRequest) request;
        int oscarCount = countLessThanOscarsRequest.getCount();

        int amount = 0;
        for (Movie movie : collectionManager.getCollection()) {
            if (movie.getOscarsCount() < oscarCount) {
                amount += 1;
            }
        }

        CountLessThanOscarsResponse countLessThanOscarsResponse = new CountLessThanOscarsResponse(amount);
        countLessThanOscarsResponse.setRequestId(request.getRequestId());
        return countLessThanOscarsResponse;
    }

    @Override
    public String getInfo() {
        return "counts the number of elements in the collection with less than the specified number of oscars";
    }
}
