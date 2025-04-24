package client.commands;

import client.UDPClient;
import client.forms.MovieForm;
import common.commands.Command;
import common.model.Movie;
import common.requests.AddIfMinRequest;
import common.responses.AddIfMinResponse;
import common.responses.NullCommandResponse;

public class AddIfMinCommand implements Command {
    private final UDPClient udpClient;

    public AddIfMinCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        Movie movie = MovieForm.ask();

        AddIfMinRequest request = new AddIfMinRequest(movie);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        AddIfMinResponse responseData = (AddIfMinResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        if (responseData.getMessage() != null) {
            System.out.println(responseData.getMessage());
        } else {
            System.out.println("No movie added.");
        }
    }
}
