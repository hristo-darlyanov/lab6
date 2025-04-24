package server.modules;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class ConnectionAcceptanceModule {
    public Selector acceptConnection(int port) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        return selector;
    }
}
