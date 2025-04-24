package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.ClearRequest;
import common.responses.ClearResponse;
import common.responses.NullCommandResponse;

public class ClearCommand implements Command {
    private final UDPClient client;

    public ClearCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        ClearRequest request = new ClearRequest();
        Object response = client.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        ClearResponse responseData = (ClearResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println(responseData.getMessage());
    }
}
