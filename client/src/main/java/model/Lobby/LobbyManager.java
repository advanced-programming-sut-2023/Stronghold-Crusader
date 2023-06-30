package model.Lobby;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import network.Connection;
import network.Request;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LobbyManager {
    public static ArrayList<Lobby> getLobbies() {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("get_lobbies");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<Lobby>>() {
        }.getType();
        return new Gson().fromJson(result, arrayListType);
    }

    public static GameRoom getGameRoom(String lobbyId) {
        return new GameRoom(null, null, null, null);
    }

    public static Lobby getLobby(String lobbyId) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("get_lobby");
        request.addParameter("id", lobbyId);
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) return null;
        return new Gson().fromJson(result, Lobby.class);
    }

    public static void createLobby(Lobby lobby) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("create_lobby");
        request.addParameter("lobby", new Gson().toJson(lobby));
        Connection.getInstance().sendRequest(request);
    }

    public static void deleteLobby(String lobbyId) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("delete_lobby");
        request.addParameter("id", lobbyId);
        Connection.getInstance().sendRequest(request);
    }

    public static boolean lobbyExists(String lobbyId) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("lobby_exists");
        request.addParameter("id", lobbyId);
        String result = Connection.getInstance().sendRequest(request);
        return Boolean.parseBoolean(result);
    }
}
