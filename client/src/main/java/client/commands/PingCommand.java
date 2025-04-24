package client.commands;

import client.modules.NetworkCommunicationModule;
import common.commands.Command;
import common.requests.PingRequest;
import common.requests.Request;
import common.responses.NullCommandResponse;
import common.responses.PingResponse;

public class PingCommand implements Command {
    private NetworkCommunicationModule commModule;

    public void PingCommand(NetworkCommunicationModule commModule) {
        this.commModule = commModule;
    }

    @Override
    public void execute(String argument) {
        Request request = new PingRequest();
        Object response = commModule.sendRequestAndReceive(request);

        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        PingResponse responseData = (PingResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println("Valid connection to server: " + responseData.serverId);
    }
}
