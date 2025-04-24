package server.commands;

import common.requests.Request;
import common.responses.ExecuteScriptResponse;
import common.responses.Response;

public class ExecuteScriptCommand implements ServerCommand {
    @Override
    public Response execute(Request request) {
        ExecuteScriptResponse response = new ExecuteScriptResponse();
        response.setRequestId(request.getRequestId());
        response.setError("ExecuteScriptCommand is not implemented on the server side.");
        return response;
    }

    @Override
    public String getInfo() {
        return "executes a script from a file";
    }
}
