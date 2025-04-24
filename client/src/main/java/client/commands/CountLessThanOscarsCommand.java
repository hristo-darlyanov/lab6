package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.CountLessThanOscarsRequest;
import common.responses.CountLessThanOscarsResponse;
import common.responses.NullCommandResponse;

public class CountLessThanOscarsCommand implements Command {
    private final UDPClient client;

    public CountLessThanOscarsCommand(UDPClient client) {
        this.client = client;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty() || argument == null) {
            System.out.println("You should enter an oscar count");
            return;
        }

        int oscarCount;

        try {
            oscarCount = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("Oscar count should be a number");
            return;
        }

        if (oscarCount <= 0) {
            System.out.println("Oscar count should be a positive number");
            return;
        }

        CountLessThanOscarsRequest request = new CountLessThanOscarsRequest(oscarCount);
        Object response = client.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        CountLessThanOscarsResponse responseData = (CountLessThanOscarsResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return;
        }

        System.out.println("Movies with less than " + oscarCount + " oscars: " + responseData.getCount());
    }
}
