package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.RemoveHeadRequest;
import common.responses.NullCommandResponse;
import common.responses.RemoveHeadResponse;
import common.responses.Response;

public class RemoveHeadCommand implements Command {
    private final UDPClient udpClient;

    public RemoveHeadCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        RemoveHeadRequest request = new RemoveHeadRequest();
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);

        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        RemoveHeadResponse responseData = (RemoveHeadResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        if (responseData.getMovie() != null) {
            System.out.println("Removed movie: " + responseData.getMovie());
        } else {
            System.out.println("No movie to remove.");
        }
    }
}
