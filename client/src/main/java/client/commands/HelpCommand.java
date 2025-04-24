package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.HelpRequest;
import common.responses.HelpResponse;
import common.responses.NullCommandResponse;
import common.responses.Response;

import java.io.IOException;

public class HelpCommand implements Command {
    private UDPClient client;
    public HelpCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        HelpRequest request = new HelpRequest();
        Object response = client.getCommModule().sendRequestAndReceive(request);

        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        HelpResponse responseData = (HelpResponse) response;

        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }
        System.out.println(responseData.helpMessage);
    }
}
