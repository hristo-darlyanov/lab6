package common.responses;

import common.commands.CommandTrigger;

public class AddResponse extends Response {
    private final String message;

    public AddResponse(String message) {
        super(CommandTrigger.ADD);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
