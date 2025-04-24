package client.commands;

import client.managers.ClientCommandManager;
import common.commands.Command;
import common.managers.FileManager;

import java.util.Map;

public class ExecuteScriptCommand implements Command {
    private static int amountOfExecutedScripts = 1;
    private int allowedRecursionDepth = 10;
    private ClientCommandManager commandManager;

    public ExecuteScriptCommand(ClientCommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Execute of 'execute_script' command
     */
    @Override
    public void execute(String argument) {
        if (argument == null || argument.isEmpty()) {
            System.out.println("You should enter a file name");
            return;
        }

        Map<String, String> commands = FileManager.loadCommandScript(argument);
        if (commands == null) {
            System.out.println("File not found or empty. Please check the file name and try again.");
            return;
        }

        for (Map.Entry<String, String> entry : commands.entrySet()) {
            String key = entry.getKey();
            String parameter = entry.getValue();
            if (key.equals("execute_script")) {
                amountOfExecutedScripts += 1;
            }
            if (amountOfExecutedScripts > allowedRecursionDepth) {
                System.out.println("Recursion detected. Command execution stopped.");
                return;
            }

            System.out.println("Executing command: " + key + " with the argument: " + parameter);
            try {
                commandManager.execute(key, parameter);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }

        amountOfExecutedScripts = 1;
    }

    /**
     * @return Info about command
     */
    @Override
    public String getInfo() {
        return "execute script from file";
    }
}
