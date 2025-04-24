package client;

import client.managers.ClientCommandManager;
import client.modules.NetworkCommunicationModule;
import client.utilities.Console;
import common.commands.CommandTrigger;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UDPClient {
    private final NetworkCommunicationModule commModule;
    private final ClientCommandManager commandManager;

    public UDPClient(String host, int port) throws Exception {
        this.commModule = new NetworkCommunicationModule(host, port);
        this.commandManager = new ClientCommandManager(this);
        commandManager.registerDefaultCommands();
    }

    public void run() {
        int maxRetries = 5;
        int retryDelay = 1000; // Initial delay in milliseconds
        boolean connected = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                String serverId = commModule.testConnection(); // Test connection to the server
                System.out.println("Connected to the server with ID: " + serverId);
                connected = true;
                break;
            } catch (IOException e) {
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Fatal error during connection: " + e.getMessage());
                return;
            }

            if (attempt < maxRetries) {
                try {
                    Thread.sleep(retryDelay);
                    // Max delay is 10 seconds
                    retryDelay = Math.min(retryDelay * 2, 10000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (!connected) {
            System.out.println("Failed to connect to the server after " + maxRetries + " attempts. Exiting...");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("UDP Client started. Type 'help' for a list of commands.");

            while (true) {
                String input = Console.readLineConsole();
                if (input.isEmpty()) continue;

                String[] tokens = input.trim().split("\\s+", 2);
                String commandName = tokens[0];
                String args = tokens.length > 1 ? tokens[1] : "";

                if (Objects.equals(commandName, CommandTrigger.EXIT)) {
                    commModule.close();
                    break;
                }

                try {
                    commandManager.execute(commandName, args);
                } catch (Exception e) {
                    if (e.getMessage() != null && e.getMessage().contains("Movie with ID")) {
                        System.out.println("Error: " + e.getMessage());
                    } else {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public NetworkCommunicationModule getCommModule() {
        return commModule;
    }
}
