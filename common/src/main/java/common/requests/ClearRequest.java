package common.requests;

import common.commands.CommandTrigger;

public class ClearRequest extends Request {
    public ClearRequest() {
        super(CommandTrigger.CLEAR);
    }
}
