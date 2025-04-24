package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.GetMovieByIdRequest;
import common.responses.GetMovieByIdResponse;
import common.responses.NullCommandResponse;
import common.utilities.Validator;

public class GetMovieByIdCommand implements Command {
    private final UDPClient udpClient;

    public GetMovieByIdCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        int id = Validator.parseAndValidateInt(argument);

        GetMovieByIdRequest request = new GetMovieByIdRequest(id);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        GetMovieByIdResponse responseData = (GetMovieByIdResponse) response;

        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }

        if (responseData.getMovie() != null) {
            System.out.println("Movie found: " + responseData.getMovie());
        } else {
            System.out.println("No movie found with the given ID.");
        }
    }
}
