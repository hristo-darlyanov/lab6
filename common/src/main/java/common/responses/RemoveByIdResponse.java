package common.responses;

import common.commands.CommandTrigger;

public class RemoveByIdResponse extends Response {
    private final String message;

    public RemoveByIdResponse(String message) {
        super(CommandTrigger.REMOVE_BY_ID);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
