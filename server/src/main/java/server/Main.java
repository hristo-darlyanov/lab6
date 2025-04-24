package server;

import server.managers.CollectionManager;
import server.managers.ServerCommandManager;

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        ServerCommandManager commandManager = new ServerCommandManager(collectionManager);
        UDPServer server = new UDPServer(commandManager, collectionManager);
        server.registerShutdownHook();
        server.run(12345);
    }
}
