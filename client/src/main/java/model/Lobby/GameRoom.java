package model.Lobby;

import javafx.scene.paint.Color;
import model.Map.MapManager;
import model.User.User;

import java.util.HashMap;
import java.util.Set;

public class GameRoom {
    private User admin;
    private final HashMap<User, Color> players = new HashMap<>();
    private final String mapId;
    private final String gameID;
    private final int capacity;

    public GameRoom(User admin, Color color, String mapId, String gameId) {
        this.admin = admin;
        this.mapId = mapId;
        this.gameID = gameId;
        this.capacity = MapManager.getMapPlayerCount(mapId);

        players.put(admin, color);
    }

    public User getAdmin() {
        return admin;
    }

    public String getMapId() {
        return mapId;
    }

    public String getGameID() {
        return gameID;
    }

    public int getPlayersCount(){
        return players.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<User> getPlayers() {
        return players.keySet();
    }
    public void  addPlayer(User user, Color color){
        players.put(user, color);
    }

    public void removePlayer(User player) {
        players.remove(player);
    }
}
