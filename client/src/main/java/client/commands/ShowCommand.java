package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.ShowRequest;
import common.responses.ShowResponse;
import common.responses.NullCommandResponse;

public class ShowCommand implements Command {
    private final UDPClient client;

    public ShowCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        ShowRequest showRequest = new ShowRequest();
        Object response = client.getCommModule().sendRequestAndReceive(showRequest);

        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        if (response instanceof NullCommandResponse) {
            NullCommandResponse nullResponse = (NullCommandResponse) response;
            System.out.println("Error: " + nullResponse.getError());
            return;
        }

        ShowResponse responseData = (ShowResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }

        // Display the collection
        System.out.println("Collection: " + responseData.getMovies().stream()
                .map(movie -> movie.toString())
                .reduce("", (acc, movie) -> acc + movie + "\n"));
    }
}
