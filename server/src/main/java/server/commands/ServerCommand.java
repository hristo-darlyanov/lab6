package server.commands;

import common.commands.Command;
import common.requests.Request;
import common.responses.Response;

public interface ServerCommand extends Command {
    Response execute(Request request);
}
