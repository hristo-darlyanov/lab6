package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.RemoveByIdRequest;
import common.responses.NullCommandResponse;
import common.responses.RemoveByIdResponse;
import common.utilities.Validator;

public class RemoveByIdCommand implements Command {
    private final UDPClient udpClient;

    public RemoveByIdCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        int id = Validator.parseAndValidateInt(argument);

        RemoveByIdRequest request = new RemoveByIdRequest(id);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        RemoveByIdResponse responseData = (RemoveByIdResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        if (responseData.getMessage() != null) {
            System.out.println(responseData.getMessage());
        } else {
            System.out.println("No movie to remove.");
        }
    }
}
