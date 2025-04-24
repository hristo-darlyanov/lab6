package common.responses;

import common.commands.CommandTrigger;

public class RemoveLowerResponse extends Response {
    private final String message;

    public RemoveLowerResponse(String message) {
        super(CommandTrigger.REMOVE_LOWER);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
