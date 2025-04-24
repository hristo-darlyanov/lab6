package common.requests;

import common.commands.CommandTrigger;

public class RemoveByIdRequest extends Request {
    private final int id;

    public RemoveByIdRequest(int id) {
        super(CommandTrigger.REMOVE_BY_ID);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
