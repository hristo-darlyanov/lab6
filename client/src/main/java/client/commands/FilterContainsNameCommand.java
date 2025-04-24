package client.commands;

import client.UDPClient;
import common.commands.Command;
import common.requests.FilterContainsNameRequest;
import common.responses.FilterContainsNameResponse;
import common.responses.NullCommandResponse;

public class FilterContainsNameCommand implements Command {
    private final UDPClient udpClient;

    public FilterContainsNameCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        if (argument == null || argument.isEmpty()) {
            System.out.println("You must specify a substring to filter by.");
            return;
        }

        FilterContainsNameRequest request = new FilterContainsNameRequest(argument);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return;
        }

        FilterContainsNameResponse responseObject = (FilterContainsNameResponse) response;
        if (responseObject.getError() != null) {
            System.out.println("Error: " + responseObject.getError());
            return;
        }
        if (responseObject.getMovies() == null || responseObject.getMovies().isEmpty()) {
            System.out.println("No movies found.");
        } else {
            System.out.println("Movies containing '" + argument + "':");
            responseObject.getMovies().forEach(movie -> System.out.println(movie));
        }
    }
}
