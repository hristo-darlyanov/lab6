package common.responses;

import common.commands.CommandTrigger;

public class ClearResponse extends Response {
    private final String message;

    public ClearResponse(String message) {
        super(CommandTrigger.CLEAR);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
