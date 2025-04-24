package server.commands;

import common.requests.Request;
import common.responses.ClearResponse;
import common.responses.Response;
import server.managers.CollectionManager;

public class ClearCommand implements ServerCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        ClearResponse clearResponse = new ClearResponse("Collection cleared successfully.");
        clearResponse.setRequestId(request.getRequestId());
        return clearResponse;
    }

    @Override
    public String getInfo() {
        return "clears the collection";
    }
}
