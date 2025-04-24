package client;

public class Main {
    public static void main(String[] args) {
        UDPClient client = null;
        try {
            client = new UDPClient("localhost", 12345);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        client.run();
    }
}