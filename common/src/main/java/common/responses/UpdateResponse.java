package common.responses;

import common.commands.CommandTrigger;

public class UpdateResponse extends Response {
    private final String message;

    public UpdateResponse(String message) {
        super(CommandTrigger.UPDATE);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
