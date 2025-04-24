package client.commands;

import client.UDPClient;
import client.forms.UpdateForm;
import common.commands.Command;
import common.model.Movie;
import common.requests.GetMovieByIdRequest;
import common.requests.UpdateRequest;
import common.responses.GetMovieByIdResponse;
import common.responses.NullCommandResponse;
import common.responses.UpdateResponse;
import common.utilities.Validator;

public class UpdateCommand implements Command {
    private final UDPClient udpClient;

    public UpdateCommand(UDPClient udpClient) {
        this.udpClient = udpClient;
    }

    @Override
    public void execute(String argument) {
        // Retrieve the ID of the movie to update
        int id = Validator.parseAndValidateInt(argument);

        Movie movie = getMovieById(id);
        if (movie == null) {
            System.out.println("Movie with ID " + id + " not found.");
            return;
        }

        Movie updatedMovie = UpdateForm.update(movie);

        UpdateRequest request = new UpdateRequest(id, updatedMovie);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return;
        }

        UpdateResponse updateResponse = (UpdateResponse) response;
        if (updateResponse.getError() != null) {
            System.out.println("Error: " + updateResponse.getError());
            return;
        }
        if (updateResponse.getMessage() != null) {
            System.out.println(updateResponse.getMessage());
        } else {
            System.out.println("No movie updated.");
        }
    }

    private Movie getMovieById(int id) {
        GetMovieByIdRequest request = new GetMovieByIdRequest(id);
        Object response = udpClient.getCommModule().sendRequestAndReceive(request);
        if (response instanceof NullCommandResponse) {
            System.out.println("Command not found: " + ((NullCommandResponse) response).getCommandName());
            return null;
        }
        if (response == null) {
            System.out.println("No response from server.");
            return null;
        }

        GetMovieByIdResponse responseData = (GetMovieByIdResponse) response;
        if (responseData.getError() != null) {
            System.out.println("Error: " + responseData.getError());
            return null;
        }

        if (responseData.getMovie() != null) {
            return responseData.getMovie();
        } else {
            System.out.println("No movie found with ID: " + id);
            return null;
        }
    }
}
