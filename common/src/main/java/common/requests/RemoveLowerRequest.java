package common.requests;

import common.commands.CommandTrigger;

public class RemoveLowerRequest extends Request {
    private final int id;

    public RemoveLowerRequest(int id) {
        super(CommandTrigger.REMOVE_LOWER);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
