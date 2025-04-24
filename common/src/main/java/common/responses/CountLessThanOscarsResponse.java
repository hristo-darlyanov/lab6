package common.responses;

import common.commands.CommandTrigger;

public class CountLessThanOscarsResponse extends Response {
    private final int count;

    public CountLessThanOscarsResponse(int count) {
        super(CommandTrigger.COUNT_LESS_THAN_OSCARS);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
