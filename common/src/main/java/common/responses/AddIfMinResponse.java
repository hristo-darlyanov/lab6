package common.responses;

import common.commands.CommandTrigger;

public class AddIfMinResponse extends Response {
    private final String message;

    public AddIfMinResponse(String message) {
        super(CommandTrigger.ADD_IF_MIN);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
