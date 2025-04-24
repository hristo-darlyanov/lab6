package common.requests;

import common.commands.CommandTrigger;

public class PingRequest extends Request {
    public PingRequest() {
        super(CommandTrigger.PING);
    }
}
