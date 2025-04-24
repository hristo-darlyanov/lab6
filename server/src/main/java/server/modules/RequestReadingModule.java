package server.modules;

import common.requests.Request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class RequestReadingModule {
    /**
     * Deserializes the ByteBuffer into a Request object.
     */
    public Request readRequest(byte[] data) throws Exception {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (Request) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error reading request: " + e.getMessage()); // Log the error
            throw e; // Re-throw the exception to be handled by the caller
        }
    }
}
