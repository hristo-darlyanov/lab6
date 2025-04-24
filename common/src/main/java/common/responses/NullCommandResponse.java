package common.responses;

public class NullCommandResponse extends Response {
    public NullCommandResponse(String commandName, String error) {
        super(commandName);
        this.setError(error);
    }
}
