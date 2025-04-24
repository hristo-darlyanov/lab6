package server.modules;

import common.requests.Request;
import common.responses.Response;
import server.managers.ServerCommandManager;

public class CommandProcessingModule {
    public Response process(ServerCommandManager serverCommandManager, Request req) {
        return serverCommandManager.getPayload(req);
    }
}