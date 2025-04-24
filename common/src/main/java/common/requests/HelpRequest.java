package common.requests;

import common.commands.CommandTrigger;

public class HelpRequest extends Request {
    public HelpRequest() {
        super(CommandTrigger.HELP);
    }
}
