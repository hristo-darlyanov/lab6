package common.responses;

import common.commands.CommandTrigger;

public class HelpResponse extends Response {
    public final String helpMessage;

    public HelpResponse(String helpMessage) {
        super(CommandTrigger.HELP);
        this.helpMessage = helpMessage;
    }
}
