package client.managers;

import client.UDPClient;
import client.commands.*;
import common.commands.Command;
import common.commands.CommandTrigger;
import common.managers.CommandManager;

public class ClientCommandManager extends CommandManager<Command> {
    private final UDPClient client;

    public ClientCommandManager(UDPClient client) {
        this.client = client;
    }

    @Override
    public void registerDefaultCommands() {
        register(CommandTrigger.HELP, new HelpCommand(client));
        register(CommandTrigger.INFO, new InfoCommand(client));
        register(CommandTrigger.SHOW, new ShowCommand(client));
        register(CommandTrigger.COUNT_BY_GENRE, new CountByGenreCommand(client));
        register(CommandTrigger.ADD, new AddCommand(client));
        register(CommandTrigger.CLEAR, new ClearCommand(client));
        register(CommandTrigger.COUNT_LESS_THAN_OSCARS, new CountLessThanOscarsCommand(client));
        register(CommandTrigger.FILTER_CONTAINS_NAME, new FilterContainsNameCommand(client));
        register(CommandTrigger.REMOVE_HEAD, new RemoveHeadCommand(client));
        register(CommandTrigger.REMOVE_LOWER, new RemoveLowerCommand(client));
        register(CommandTrigger.REMOVE_BY_ID, new RemoveByIdCommand(client));
        register(CommandTrigger.ADD_IF_MIN, new AddIfMinCommand(client));
        register(CommandTrigger.EXECUTE_SCRIPT, new ExecuteScriptCommand(this));
        register(CommandTrigger.GET_MOVIE_BY_ID, new GetMovieByIdCommand(client));
        register(CommandTrigger.UPDATE, new UpdateCommand(client));
    }

    public void execute(String commandName, String argument) {
        Command command = getCommands().get(commandName);
        if (command != null) {
            command.execute(argument);
        } else {
            throw new IllegalArgumentException("Command not found: " + commandName);
        }
    }
}
