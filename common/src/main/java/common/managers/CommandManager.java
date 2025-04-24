package common.managers;

import common.commands.Command;
import common.requests.Request;
import common.responses.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Command manager
 * Manages commands
 */
public abstract class CommandManager<T extends Command> {
    private final Map<String, T> commandMap;

    public CommandManager() {
        this.commandMap = new HashMap<>();
    }


    /**
     * Register a command
     *
     * @param commandName Name of the command
     * @param command     Command
     */
    public void register(String commandName, T command) {
        commandMap.put(commandName, command);
    }

    /**
     * Register default commands
     */
    public abstract void registerDefaultCommands();

    /**
     * Get all commands
     *
     * @return Map of commands
     */
    public Map<String, T> getCommands() {
        return commandMap;
    }
}
