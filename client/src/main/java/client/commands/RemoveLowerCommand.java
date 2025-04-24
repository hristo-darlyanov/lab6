package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.RemoveLowerRequest;
import common.responses.NullCommandResponse;
import common.responses.RemoveLowerResponse;
import common.utilities.Validator;

public class RemoveLowerCommand implements Command {
    private final UDPClient udpClient;

    public RemoveLowerCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        int id = Validator.parseAndValidateInt(argument);

        RemoveLowerRequest request = new RemoveLowerRequest(id);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        RemoveLowerResponse responseData = (RemoveLowerResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println(responseData.getMessage());
    }
}
