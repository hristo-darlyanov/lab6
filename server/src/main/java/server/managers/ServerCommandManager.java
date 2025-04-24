package server.managers;

import common.commands.CommandTrigger;
import common.managers.CommandManager;
import common.requests.Request;
import common.responses.NullCommandResponse;
import common.responses.Response;
import server.commands.*;

import java.util.Map;

public class ServerCommandManager extends CommandManager<ServerCommand> {
    private final CollectionManager collectionManager;
    public ServerCommandManager(CollectionManager collectionManager) {
        super();
        this.collectionManager = collectionManager;
    }

    public Response getPayload(Request request) {
        String commandName = request.getCommandName();
        Map<String, ServerCommand> commands = getCommands();
        if (commandName == null) {
            return new NullCommandResponse(null, "Command name is empty");
        }
        if (commands.containsKey(commandName)) {
            return commands.get(commandName).execute(request);
        } else {
            return new NullCommandResponse(commandName, "Command not found");
        }
    }

    @Override
    public void registerDefaultCommands() {
        register(CommandTrigger.HELP, new HelpCommand(this));
        register(CommandTrigger.INFO, new InfoCommand(collectionManager));
        register(CommandTrigger.SHOW, new ShowCommand(collectionManager));
        register(CommandTrigger.COUNT_BY_GENRE, new CountByGenreCommand(collectionManager));
        register(CommandTrigger.ADD, new AddCommand(collectionManager));
        register(CommandTrigger.CLEAR, new ClearCommand(collectionManager));
        register(CommandTrigger.COUNT_LESS_THAN_OSCARS, new CountLessThanOscarsCommand(collectionManager));
        register(CommandTrigger.FILTER_CONTAINS_NAME, new FilterContainsNameCommand(collectionManager));
        register(CommandTrigger.REMOVE_HEAD, new RemoveHeadCommand(collectionManager));
        register(CommandTrigger.REMOVE_LOWER, new RemoveLowerCommand(collectionManager));
        register(CommandTrigger.REMOVE_BY_ID, new RemoveByIdCommand(collectionManager));
        register(CommandTrigger.ADD_IF_MIN, new AddIfMinCommand(collectionManager));
        register(CommandTrigger.EXECUTE_SCRIPT, new ExecuteScriptCommand());
        register(CommandTrigger.GET_MOVIE_BY_ID, new GetMovieByIdCommand(collectionManager));
        register(CommandTrigger.UPDATE, new UpdateCommand(collectionManager));
    }
}
