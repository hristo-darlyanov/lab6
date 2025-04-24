package common.responses;

import common.commands.CommandTrigger;

public class CountByGenreResponse extends Response {
    private final int count;

    public CountByGenreResponse(int count) {
        super(CommandTrigger.COUNT_BY_GENRE);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
