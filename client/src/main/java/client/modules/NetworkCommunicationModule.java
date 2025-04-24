package client.modules;

import common.requests.Request;
import common.requests.PingRequest;
import common.responses.PingResponse;
import common.responses.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NetworkCommunicationModule {
    private final DatagramChannel channel;
    private final SocketAddress serverAddress;

    public NetworkCommunicationModule(String host, int port) throws IOException {
        this.serverAddress = new InetSocketAddress(host, port);
        this.channel = DatagramChannel.open();
        this.channel.configureBlocking(false);
    }

    public void sendRequest(Request request) throws IOException {
        byte[] data;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(request);
            data = baos.toByteArray();
        }
        if (data.length > 8192) { // Validate size to avoid sending oversized data
            throw new IOException("Request size exceeds maximum allowed size: " + data.length);
        }
        ByteBuffer buffer = ByteBuffer.wrap(data); // Wrap the serialized data directly
        channel.send(buffer, serverAddress);
    }

    public Response receiveResponse() {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        try {
            SocketAddress address = channel.receive(buffer);
            if (address == null) return null;

            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data); // Read the entire packet data
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                Response response = (Response) ois.readObject();
                //System.out.println("Received response from server"); // Debug log
                return response;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error receiving response: " + e.getMessage()); // Debug log
            return null;
        }
    }

    public Response sendRequestAndReceive(Request request) {
        try {
            sendRequest(request);
            //System.out.println("Request ID: " + request.getRequestId()); // Print request ID

            long start = System.currentTimeMillis();
            Response response = null;

            while (System.currentTimeMillis() - start < 5000) { // 5-second timeout
                response = receiveResponse();
                if (response != null) {
                    // Check if the response contains an error
                    if (response.getError() != null) {
                        System.out.println("Server error: " + response.getError());
                        return null; // Return null to indicate an error occurred
                    }
                    if (request.getRequestId().equals(response.getRequestId())) {
                        return response; // Return only if IDs match
                    } else {
                        System.out.println("Mismatched response ID. Ignoring...");
                    }
                }
                Thread.sleep(50); // Wait briefly before retrying
            }

            System.out.println("Timeout: No valid response received within 5 seconds.");
        } catch (Exception e) {
            System.out.println("Failed to communicate with server: " + e.getMessage());
        }
        return null;
    }

    public String testConnection() throws IOException {
        PingRequest pingRequest = new PingRequest(); // Create a PingRequest object
        PingResponse response = (PingResponse) sendRequestAndReceive(pingRequest); // Send the request and wait for a response

        if (response == null) {
            throw new IOException("Server is unreachable or did not respond correctly.");
        }
        if (response.getError() != null) {
            throw new IOException("Server responded with an error: " + response.getError());
        }

        return response.serverId;
    }

    public void close() throws IOException {
        channel.close();
    }
}
