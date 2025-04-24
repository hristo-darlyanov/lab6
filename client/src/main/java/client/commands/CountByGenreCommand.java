package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.model.MovieGenre;
import common.requests.CountByGenreRequest;
import common.responses.CountByGenreResponse;
import common.responses.NullCommandResponse;

public class CountByGenreCommand implements Command {
    private final UDPClient client;

    public CountByGenreCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        if (!MovieGenre.contains(argument)) {
            if (argument.isEmpty()) {
                System.out.println("Genre must be provided");
            } else {
                System.out.println("There is no such genre");
            }
            return;
        }

        MovieGenre genre = MovieGenre.valueOf(argument.toUpperCase());
        CountByGenreRequest request = new CountByGenreRequest(genre);
        Object response = client.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        CountByGenreResponse responseData = (CountByGenreResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println("Count by genre: " + responseData.getCount());
    }
}
