package common.responses;

import common.commands.CommandTrigger;

public class ExecuteScriptResponse extends Response {
    public ExecuteScriptResponse() {
        super(CommandTrigger.EXECUTE_SCRIPT);
    }
}
