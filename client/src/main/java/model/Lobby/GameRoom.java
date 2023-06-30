package model.Lobby;

import model.Map.Map;
import model.Map.MapManager;
import model.User.User;
import model.enums.User.Color;

import java.util.HashMap;
import java.util.Set;

public class GameRoom {
    private final String gameID;
    private  User admin;
    private final HashMap<User, Color> players = new HashMap<>();
    private final Map map;
    private final String mapId;
    private final int capacity;

    public GameRoom(User admin, Color color, String mapID, String gameID) {
        this.admin = admin;
        this.map = MapManager.load(mapID);
        assert map != null;
        capacity = map.getPlayerCount();
        players.put(admin, color);
        this.gameID = gameID;
        this.mapId = mapID;

    }

    public boolean isColorPicked(Color color) {
        return players.containsValue(color);
    }

    public void addPlayer(User player, Color color) {
        players.put(player, color);
    }

    public void removePlayer(User player) {
        players.remove(player);
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public boolean isAdmin(User player){
        return admin.equals(player);
    }

    public int getPlayersCount() {
        return players.size();
    }

    public String getGameID() {
        return gameID;
    }

    public User getAdmin() {
        return admin;
    }

    public String getMapId() {
        return mapId;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<User> getPlayers() {
        return players.keySet();
    }
}
