package server.modules;

import common.responses.Response;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ResponseSendingModule {
    /**
     * Serializes the Response object into a ByteBuffer.
     */
    public ByteBuffer prepareResponse(Response resp) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(resp);
        }
        byte[] data = baos.toByteArray();
        return ByteBuffer.wrap(data); // Wrap the serialized data directly
    }
}

