package common.requests;

import common.commands.CommandTrigger;

public class InfoRequest extends Request {
    public InfoRequest() {
        super(CommandTrigger.INFO);
    }
}
