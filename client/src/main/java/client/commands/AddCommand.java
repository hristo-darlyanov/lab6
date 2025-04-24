package client.commands;

import client.UDPClient;
import client.forms.MovieForm;
import common.commands.Command;
import common.model.Movie;
import common.requests.AddRequest;
import common.responses.AddResponse;
import common.responses.NullCommandResponse;

public class AddCommand implements Command {
    private final UDPClient client;

    public AddCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        Movie movie = MovieForm.ask();
        AddRequest addRequest = new AddRequest(movie);
        Object response = client.getCommModule().sendRequestAndReceive(addRequest);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        AddResponse responseData = (AddResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println(responseData.getMessage());
    }
}
