package common.requests;

import common.commands.CommandTrigger;

public class CountLessThanOscarsRequest extends Request {
    private final int count;

    public CountLessThanOscarsRequest(int count) {
        super(CommandTrigger.COUNT_LESS_THAN_OSCARS);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
