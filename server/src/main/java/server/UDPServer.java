package server;

import common.commands.CommandTrigger;
import common.requests.Request;
import common.requests.PingRequest;
import common.responses.PingResponse;
import common.responses.Response;
import common.utilities.ProjectInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.managers.CollectionManager;
import server.managers.ServerCommandManager;
import server.modules.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;

public class UDPServer {
    private final ServerCommandManager commandManager;
    private final CollectionManager collectionManager;

    private static final Logger logger = LoggerFactory.getLogger(UDPServer.class);

    private final ConnectionAcceptanceModule connModule = new ConnectionAcceptanceModule();
    private final RequestReadingModule readModule = new RequestReadingModule();
    private final CommandProcessingModule procModule = new CommandProcessingModule();
    private final ResponseSendingModule sendModule = new ResponseSendingModule();
    private final ServerInfo serverInfo = new ServerInfo();

    public UDPServer(ServerCommandManager commandManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.commandManager.registerDefaultCommands();
        this.collectionManager = collectionManager;
        this.collectionManager.addMoviesFromFile(ProjectInfo.SAVE_FILE);
        logger.info("Elements loaded: {}", collectionManager.getCollection().size());
    }

    /**
     * Starts the non-blocking UDP server on the given port.
     */

    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                collectionManager.save();
                logger.info("Collection saved during shutdown.");
            } catch (Exception e) {
                logger.error("Failed to save collection during shutdown.", e);
            }
        }));
    }

    public void run(int port) {
        try {
            Selector selector = connModule.acceptConnection(port); // Use connModule to accept connections
            logger.info("Server started with ID: {}", serverInfo.getServerId());
            logger.info("Server listening on UDP port {}", port);

            Map<InetSocketAddress, ByteBuffer> clientBuffers = new HashMap<>(); // Map to store client-specific buffers

            while (true) {
                try {
                    selector.select(); // Wait for events
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();

                        if (key.isReadable()) {
                            DatagramChannel channel = (DatagramChannel) key.channel();
                            InetSocketAddress clientAddress = null;

                            try {
                                ByteBuffer buffer = ByteBuffer.allocate(8192); // Allocate a new buffer for each request
                                clientAddress = (InetSocketAddress) channel.receive(buffer);
                                if (clientAddress == null) continue;

                                buffer.flip(); // Prepare buffer for reading
                                byte[] data = new byte[buffer.remaining()];
                                buffer.get(data); // Read the entire packet data

                                Request req = readModule.readRequest(data); // Adjusted to accept byte array
                                logger.info("Received {} from {}", req.getCommandName(), clientAddress);

                                Response resp;
                                if (req instanceof PingRequest) { // Handle PingRequest
                                    resp = new PingResponse(serverInfo.getServerId());
                                } else {
                                    resp = procModule.process(commandManager, req);
                                }

                                // Set the requestId in the response to match the request
                                resp.setRequestId(req.getRequestId());
                                logger.info("Processing request: {}", req.getRequestId());
                                logger.info("Responding with {}", resp.getRequestId());

                                ByteBuffer responseBuffer = sendModule.prepareResponse(resp);
                                channel.send(responseBuffer, clientAddress);
                                logger.info("Sent response for: {} to {}", resp.getCommandName(), clientAddress);

                                if (Objects.equals(req.getCommandName(), CommandTrigger.EXIT)) {
                                    logger.info("Exit command received from {}. Shutting down.", clientAddress);
                                    collectionManager.save(); // Save the collection before shutdown
                                    logger.info("Collection saved on shutdown");
                                    return;
                                }
                            } catch (Exception e) {
                                logger.error("Error processing request from client: {}", clientAddress, e);
                            }
                        }
                    }
                } catch (IOException e) {
                    logger.error("Error during selector operation", e);
                }
            }
        } catch (Exception e) {
            logger.error("Critical server error", e);
        }
    }
}
