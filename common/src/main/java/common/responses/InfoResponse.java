package common.responses;

import common.commands.CommandTrigger;

public class InfoResponse extends Response {
    public final String info;

    public InfoResponse(String info) {
        super(CommandTrigger.INFO);
        this.info = info;
    }
}
