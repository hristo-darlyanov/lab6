package common.commands;

/**
 * Interface for commands
 */
public interface Command {
    default void execute(String argument) {};
    default String getInfo() {
        return "No information available";
    }
}
