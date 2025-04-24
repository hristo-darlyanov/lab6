package common.requests;

import common.commands.CommandTrigger;

public class RemoveHeadRequest extends Request {
    public RemoveHeadRequest() {
        super(CommandTrigger.REMOVE_HEAD);
    }
}
