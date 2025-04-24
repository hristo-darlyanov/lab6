package server.modules;

import java.util.UUID;

public class ServerInfo {
    private final String serverId = UUID.randomUUID().toString();
    private final String startTime = String.valueOf(System.currentTimeMillis());

    public String getServerId() {
        return serverId;
    }

    public String getStartTime() {
        return startTime;
    }
}
