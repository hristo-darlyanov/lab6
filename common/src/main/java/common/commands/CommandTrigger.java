package common.commands;

public interface CommandTrigger {
    String SAVE = "save";
    String ADD = "add";
    String ADD_IF_MIN = "add_if_min";
    String UPDATE = "update";
    String CLEAR = "clear";
    String COUNT_BY_GENRE = "count_by_genre";
    String COUNT_LESS_THAN_OSCARS = "count_less_than_oscars";
    String EXECUTE_SCRIPT = "execute_script";
    String FILTER_CONTAINS_NAME = "filter_contains_name";
    String REMOVE_BY_ID = "remove_by_id";
    String REMOVE_HEAD = "remove_head";
    String REMOVE_LOWER = "remove_lower";
    String HELP = "help";
    String INFO = "info";
    String SHOW = "show";
    String EXIT = "exit";
    String PING = "ping";
    String GET_MOVIE_BY_ID = "get_movie_by_id";
}
