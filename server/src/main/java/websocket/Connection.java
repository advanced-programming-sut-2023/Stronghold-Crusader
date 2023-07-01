package websocket;

import com.google.gson.Gson;
import database.ChatManager;
import database.Database;
import database.MapManager;
import model.Color;
import model.Lobby;
import model.Request;
import model.User;
import model.*;
import model.Television.ResourceManager;
import model.Television.SaveData;
import model.chatRoom.Chat;
import utils.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class Connection extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private User loggedInUser;

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

    public void closedConnection() {
        System.out.println("Disconnected " + socket.getInetAddress() + socket.getPort());
        if (loggedInUser != null) {
            loggedInUser.setSocket(null);
            loggedInUser = null;
        }
    }

    @Override
    public void run() {
        Request request;
        try {
            while (true) {
                try {
                    request = new Gson().fromJson(inputStream.readUTF(), Request.class);
                } catch (EOFException e) {
                    closedConnection();
                    return;
                }
                switch (request.getType()) {
                    case "connect":
                        handelConnection(request);
                        break;
                    case "user_query":
                        handelUsersQuery(request);
                        break;
                    case "user_change":
                        handelUsersChange(request);
                        break;
                    case "chat":
                        handelChat(request);
                        break;
                    case "friend":
                        handelFriend(request);
                        break;
                    case "scoreboard":
                        handelScoreboard();
                        break;
                    case "lobby":
                        handelLobby(request);
                        break;
                    case "lobby_change":
                        handelLobbyChange(request);
                    case "game":
                        handelGame();
                        break;
                    case "television":
                        handleTelevision(request);
                        break;
                    case "map":
                        handelMap(request);
                        break;
                    default:
                        outputStream.writeUTF("400: bad request");
                }
            }
        } catch (SocketException socketException) {
            closedConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void handelConnection(Request request) throws IOException {
        User user = Database.getInstance().getUser(request.getParameters().get("username"));
        if (user == null) {
            outputStream.writeUTF("400: no_user");
            return;
        }
        switch (request.getCommand()) {
            case "login":
                if (user.isOnline()) {
                    outputStream.writeUTF("400: Already logged in");
                    return;
                }
                user.setSocket(socket);
                loggedInUser = user;
                outputStream.writeUTF("200: Sign in success");
                break;
            case "logout":
                loggedInUser = null;
                user.setSocket(null);
                outputStream.writeUTF("200: Log out success");
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
    }

    private void handelUsersQuery(Request request) throws IOException {
        switch (request.getCommand()) {
            case "get_user":
                User user = Database.getInstance().getUser(request.getParameters().get("username"));
                if (user == null) outputStream.writeUTF("400: no_user");
                else outputStream.writeUTF(new Gson().toJson(user));
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
            case "get_online_users":
                outputStream.writeUTF(new Gson().toJson(Database.getInstance().getOnlineUsers()));
                break;
            case "user_online":
                outputStream.writeUTF(String.valueOf(
                        Database.getInstance().getUser(request.getParameters().get("username")).isOnline()));
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
    }

    private void handelUsersChange(Request request) throws IOException {
        if (request.getCommand().equals("add")) {
            Database.getInstance().addUser(new Gson().fromJson(request.getParameters().get("user"), User.class));
            outputStream.writeUTF("200: successfully added");
            return;
        }
        User user = Database.getInstance().getUser(request.getParameters().get("username"));
        if (user == null) {
            outputStream.writeUTF("400: no_user");
            return;
        }
        switch (request.getCommand()) {
            case "highscore":
                user.setHighScore(Integer.parseInt(request.getParameters().get("highscore")));
                break;
            case "username":
                user.changeUsername(request.getParameters().get("new_username"));
                break;
            case "nickname":
                user.changeNickname(request.getParameters().get("nickname"));
                break;
            case "slogan":
                user.changeSlogan(request.getParameters().get("slogan"));
                break;
            case "email":
                user.changeEmail(request.getParameters().get("email"));
                break;
            case "password_recovery":
                user.setPasswordRecovery(new Gson().fromJson(request.getParameters().get("recovery"), Pair.class));
                break;
            case "setPassword":
                user.setPassword(request.getParameters().get("password"));
                break;
            case "removeSlogan":
                user.removeSlogan();
                break;
            case "set_avatar":
                user.setAvatarPath(request.getParameters().get("avatar_path"));
                break;
            default:
                outputStream.writeUTF("400: bad request");
                return;
        }
        outputStream.writeUTF("200: success");
    }

    private void handelChat(Request request) throws IOException {
        switch (request.getCommand()) {
            case "update_chat":
                Chat chat = new Gson().fromJson(request.getParameters().get("chat"), Chat.class);
                ChatManager.updateChat(chat, chat.getChatMode());
                outputStream.writeUTF("200: successfully updated");
                ChatManager.notifyAllMembers(chat);
                break;
            case "get_global_chat":
                outputStream.writeUTF(new Gson().toJson(ChatManager.loadGlobalChat()));
                break;
            case "get_private_chats":
                outputStream.writeUTF(new Gson().toJson(ChatManager.loadPrivateChats()));
                break;
            case "get_room_chats":
                outputStream.writeUTF(new Gson().toJson(ChatManager.loadRoomChats()));
                break;
            case "load_chat":
                String out1 = new Gson().toJson(ChatManager.loadChat(request.getParameters().get("chat_id"),
                        new Gson().fromJson(request.getParameters().get("chat_type"), Chat.ChatMode.class)));
                outputStream.writeUTF(out1);
                break;

        }
    }

    private void handelFriend(Request request) throws IOException {
        User user = Database.getInstance().getUser(request.getParameters().get("username"));
        if (user == null) {
            outputStream.writeUTF("400: no_user");
            return;
        }
        switch (request.getCommand()) {
            case "add_friend":
                user.addFriend((new Gson().fromJson(request.getParameters().get("user"), User.class)));
                break;
            case "remove_friend":
                user.removeFriend((new Gson().fromJson(request.getParameters().get("user"), User.class)));
                break;
            case "add_sender":
                user.addSender((new Gson().fromJson(request.getParameters().get("user"), User.class)));
                break;
            case "remove_sender":
                user.removeSender((new Gson().fromJson(request.getParameters().get("user"), User.class)));
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
        outputStream.writeUTF("200: success");
    }

    private void handelScoreboard() {

    }

    private void handelLobby(Request request) throws IOException {
        Lobby lobby;
        switch (request.getCommand()) {
            case "create_lobby":
                Database.getInstance().addLobby(new Gson().fromJson(request.getParameters().get("lobby"), Lobby.class));
                outputStream.writeUTF("200: success");
                break;
            case "delete_lobby":
                Database.getInstance().removeLobby(request.getParameters().get("id"));
                outputStream.writeUTF("200: success");
                break;
            case "get_lobbies":
                outputStream.writeUTF(new Gson().toJson(Database.getInstance().getAllLobbies()));
                break;
            case "get_lobby":
                lobby = Database.getInstance().getLobby(request.getParameters().get("id"));
                if (lobby == null) outputStream.writeUTF("400: no_lobby");
                else outputStream.writeUTF(new Gson().toJson(lobby));
                break;
            case "lobby_exists":
                lobby = Database.getInstance().getLobby(request.getParameters().get("id"));
                outputStream.writeUTF(String.valueOf(lobby != null));
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
    }

    private void handelLobbyChange(Request request) throws IOException {
        Map<String, String> parameters = request.getParameters();
        Lobby lobby = Database.getInstance().getLobby(parameters.get("id"));
        if (lobby == null) {
            outputStream.writeUTF("400: no_user");
            return;
        }
        switch (request.getCommand()) {
            case "add_player":
                lobby.addPlayer(new Gson().fromJson(parameters.get("player"), User.class),
                        Color.values()[Integer.parseInt(parameters.get("color"))]);
                break;
            case "remove_player":
                lobby.removePlayer(new Gson().fromJson(parameters.get("player"), User.class));
                break;
            case "set_admin":
                lobby.setAdmin(new Gson().fromJson(parameters.get("player"), User.class));
                break;
            case "change_status":
                lobby.setLobbyStatus((lobby.getLobbyStatus().equals(LobbyStatus.PRIVATE)
                        ? LobbyStatus.PUBLIC : LobbyStatus.PRIVATE));
                break;
            default:
                outputStream.writeUTF("400: bad request");
                break;
        }
        outputStream.writeUTF("200: success");
    }

    private void handleTelevision(Request request) throws Exception {
        String id = new Gson().fromJson(request.getParameters().get("id"), String.class);
        String fileName = new Gson().fromJson(request.getParameters().get("filename"), String.class);

        switch (request.getCommand()) {
            case "save":
                SaveData saveData = new Gson().fromJson(request.getParameters().get("saveData"), SaveData.class);
                ResourceManager.save(saveData, id, fileName);
                outputStream.writeUTF("200: success");
                break;
            case "load":
                SaveData data = (SaveData) ResourceManager.load(id, fileName);
                outputStream.writeUTF(new Gson().toJson(data));
                break;
            default:
                outputStream.writeUTF("400: bad request");
                break;
        }
    }

    private void handelGame() {

    }

    private void handelMap(Request request) throws IOException {
        Map<String, String> parameters = request.getParameters();
        switch (request.getCommand()) {
            case "create_map":
                MapManager.saveMap(parameters.get("map"), parameters.get("id"));
                outputStream.writeUTF("200: success");
                break;
            case "load_map":
                outputStream.writeUTF(MapManager.loadMap(parameters.get("id")));
                break;
            case "add_map":
                loggedInUser.addMap(parameters.get("id"));
                break;
            default:
                outputStream.writeUTF("400: bad request");
        }
    }
}
