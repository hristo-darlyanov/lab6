package common.requests;

import java.io.Serializable;
import java.util.UUID;

public abstract class Request implements Serializable {
    private final String commandName;
    private final String requestId = UUID.randomUUID().toString();

    public Request(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                '}';
    }

    public String getCommandName() {
        return commandName;
    }

    public String getRequestId() {
        return requestId;
    }
}
