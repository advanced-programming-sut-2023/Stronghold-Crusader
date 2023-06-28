package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private static Connection instance;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public static void connect(String host, int port) {
        instance = new Connection(host, port);
        System.out.println("Successfully connected to " + host + port);
    }

    private Connection(String host, int port) {
        try {
            socket = new Socket(host, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getInstance() {
        return instance;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public String sendRequest(Request request){
        try {
            outputStream.writeUTF(request.toJson());
            return inputStream.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
