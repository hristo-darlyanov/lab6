package server.commands;

import common.commands.Command;
import common.requests.Request;
import common.responses.HelpResponse;
import common.responses.Response;
import server.managers.ServerCommandManager;

import java.util.Map;

public class HelpCommand implements ServerCommand {
    private final ServerCommandManager manager;

    public HelpCommand(ServerCommandManager manager) {
        this.manager = manager;
    }

    @Override
    public Response execute(Request request) {
        Map<String, ServerCommand> commands = manager.getCommands();
        StringBuilder message = new StringBuilder();
        for (Map.Entry<String, ServerCommand> entry : commands.entrySet()) {
            message.append(entry.getKey()).append(" - ").append(entry.getValue().getInfo()).append("\n");
        }
        HelpResponse helpResponse = new HelpResponse(message.toString());
        helpResponse.setRequestId(request.getRequestId());
        return helpResponse;
    }

    @Override
    public String getInfo() {
        return "show all available commands";
    }
}
