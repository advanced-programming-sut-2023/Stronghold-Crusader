package websocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

@SuppressWarnings("resource")
public class Server {
    public static void main(String[] args) throws IOException {
        run(8080);
    }

    private static void run(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket socket = serverSocket.accept();
            new Connection(socket).start();
        }
    }
}
