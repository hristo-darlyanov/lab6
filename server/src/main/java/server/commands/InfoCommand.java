package server.commands;

import common.requests.Request;
import common.responses.InfoResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class InfoCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String collectionType = collectionManager.getCollection().getClass().getSimpleName();
        String initializationDate = collectionManager.getInitializationDate().toString();
        String numberOfElements = String.valueOf(collectionManager.getCollection().size());

        String message = "Collection type: " + collectionType + "\n" +
                "Initialization date: " + initializationDate + "\n" +
                "Number of elements: " + numberOfElements;

        InfoResponse infoResponse = new InfoResponse(message);
        infoResponse.setRequestId(request.getRequestId());

        return infoResponse;
    }

    @Override
    public String getInfo() {
        return "show information about the collection";
    }
}
