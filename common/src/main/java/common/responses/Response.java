package common.responses;

import java.io.Serializable;

public abstract class Response implements Serializable {
    private final String commandName;
    private String error = null;
    private String requestId = null;

    public Response(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return "Response{" +
                "commandName='" + commandName + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }

    public String getCommandName() {
        return commandName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
