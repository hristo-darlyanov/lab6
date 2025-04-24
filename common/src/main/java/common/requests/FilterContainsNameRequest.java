package common.requests;

import common.commands.CommandTrigger;

public class FilterContainsNameRequest extends Request {
    private final String name;

    public FilterContainsNameRequest(String name) {
        super(CommandTrigger.FILTER_CONTAINS_NAME);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
