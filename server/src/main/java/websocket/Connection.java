package websocket;

import com.google.gson.Gson;
import database.ChatManager;
import database.Database;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Request;
import model.User;
import model.chatRoom.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection extends Thread {
    private Socket socket;
    private String jwsToken;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Connection(Socket socket) {
        this.socket = socket;
        System.out.println("Connection from " + socket.getInetAddress() + socket.getPort());
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Request request;
        try {
            while (true) {
                request = new Gson().fromJson(inputStream.readUTF(), Request.class);
                switch (request.getType()) {
                    case "login":
                        handelLogin(request);
                        break;
                    case "users_query":
                        handelUsersQuery(request);
                        break;
                    case "chat":
                        handelChat();
                        break;
                    case "friend":
                        handelFriend();
                        break;
                    case "scoreboard":
                        handelScoreboard();
                        break;
                    case "lobby":
                        handelLobby();
                        break;
                    case "game":
                        handelGame();
                        break;
                    default:
                        outputStream.writeUTF("400: bad request");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handelLogin(Request request) throws IOException {
        request.getParameters().get("password");
        jwsToken = Jwts.builder().setIssuer(request.getParameters().get("username"))
                .signWith(SignatureAlgorithm.ES256,
                        "JXJHHxjK3vpZtfsS0P4jhAJ1pdaU9e+xnTFfK2cwrO4=".getBytes(StandardCharsets.UTF_8))
                .compact();
        outputStream.writeUTF(jwsToken);
    }

    private void handelUsersQuery(Request request) throws IOException {
        switch (request.getCommand()) {
            case "get_user":
                User user = Database.getInstance().getUser(request.getParameters().get("username"));
                if(user == null) outputStream.writeUTF("no_user");
                else outputStream.writeUTF(new Gson().toJson(user));
                break;
            case "add_user":
                Database.getInstance().addUser(new Gson().fromJson(request.getParameters().get("user"), User.class));
                outputStream.writeUTF("200: successfully added");
                break;
            case "user_exists":
                outputStream.writeUTF(String.valueOf(
                        Database.getInstance().userExists(request.getParameters().get("username"))));
                break;
            case "email_exists":
                outputStream.writeUTF(String.valueOf(
                        Database.getInstance().emailExists(request.getParameters().get("email"))));
                break;
            case "get_users":
                outputStream.writeUTF(new Gson().toJson(Database.getInstance().getUsers()));
                break;
            case "get_rankings":
                outputStream.writeUTF(new Gson().toJson(Database.getInstance().getUserRankings()));
                break;
            case "get_user_rank":
                outputStream.writeUTF(String.valueOf(
                        Database.getInstance().getUserRank(request.getParameters().get("username"))));
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
    }

    private void handelChat(Request request) {
        switch (request.getCommand()){
            case "update_chat":
                Chat chat = new Gson().fromJson(request.getParameters().get("chat"), Chat.class);
                ChatManager.updateChat(chat, chat.getChatMode());
                break;
        }
    }

    private void handelFriend() {

    }

    private void handelScoreboard() {

    }

    private void handelLobby() {

    }

    private void handelGame() {

    }
}
