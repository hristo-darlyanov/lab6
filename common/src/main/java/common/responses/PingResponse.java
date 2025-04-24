package common.responses;

public class PingResponse extends Response {
    public final String serverId;

    public PingResponse(String serverId) {
        super(null);
        this.serverId = serverId;
    }
}
