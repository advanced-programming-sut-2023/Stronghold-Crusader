package model.Lobby;

import com.google.gson.Gson;
import model.Map.MapManager;
import model.User.User;
import model.enums.User.Color;
import network.Connection;
import network.Request;

import java.util.HashMap;
import java.util.Set;

public class Lobby {
    private final int capacity;
    private final String id;
    private User admin;
    private final HashMap<User, Color> players = new HashMap<>();
    private final String mapId;

    public Lobby(String id, User admin, Color color, String mapID) {
        this.id = id;
        this.admin = admin;
        capacity = MapManager.getMapPlayerCount(mapID);
        players.put(admin, color);
        this.mapId = mapID;
    }

    public boolean isColorPicked(Color color) {
        return players.containsValue(color);
    }

    public void addPlayer(User player, Color color) {
        players.put(player, color);
        Request request = new Request();
        request.setType("lobby_change");
        request.setCommand("add_player");
        request.addParameter("id", String.valueOf(id));
        request.addParameter("player", new Gson().toJson(player));
        request.addParameter("color", String.valueOf(color.ordinal()));
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) {
            try {
                throw new Exception("Lobby doesn't exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removePlayer(User player) {
        players.remove(player);
        Request request = new Request();
        request.setType("lobby_change");
        request.setCommand("remove_player");
        request.addParameter("id", id);
        request.addParameter("player", new Gson().toJson(player));
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) {
            try {
                throw new Exception("Lobby doesn't exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setAdmin(User admin) {
        this.admin = admin;
        Request request = new Request();
        request.setType("lobby_change");
        request.setCommand("set_admin");
        request.addParameter("id", id);
        request.addParameter("player", new Gson().toJson(admin));
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) {
            try {
                throw new Exception("Lobby doesn't exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getId() {
        return id;
    }

    public User getAdmin() {
        return admin;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getMapId() {
        return mapId;
    }

    public String getColor(User user) {
        return players.get(user).toString();
    }

    public int getPlayersCount() {
        return players.size();
    }

    public Set<User> getPlayers() {
        return players.keySet();
    }
}
