package model.Lobby;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import network.Connection;
import network.Request;

import java.lang.reflect.Type;
import java.util.Set;

public class LobbyManager {

    public static Set<Lobby> getGameRooms() {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("get_lobbies");
        Type setType = new TypeToken<Set<Lobby>>() {
        }.getType();

        return new Gson().fromJson(Connection.getInstance().sendRequest(request), setType);
    }

    public static Lobby getGameRoom(String gameId) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("get_lobby");
        request.addParameter("id", gameId);
        return new Gson().fromJson(Connection.getInstance().sendRequest(request), Lobby.class);

    }

    public static void addLobby(Lobby lobby) {
        Request request = new Request();
        request.setType("lobby");
        request.setCommand("create_lobby");
        request.addParameter("lobby", new Gson().toJson(lobby));
        Connection.getInstance().sendRequest(request);
    }
}
