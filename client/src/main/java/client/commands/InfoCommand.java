package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.InfoRequest;
import common.responses.InfoResponse;
import common.responses.NullCommandResponse;

public class InfoCommand implements Command {
    private UDPClient client;
    public InfoCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        InfoRequest request = new InfoRequest();
        Object response = client.getCommModule().sendRequestAndReceive(request);

        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        InfoResponse responseData = (InfoResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println(responseData.info);
    }
}
