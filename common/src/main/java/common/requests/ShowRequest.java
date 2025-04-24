package common.requests;

import common.commands.CommandTrigger;

public class ShowRequest extends Request {
    public ShowRequest() {
        super(CommandTrigger.SHOW);
    }
}
