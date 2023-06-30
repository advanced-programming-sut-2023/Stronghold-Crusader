package model;

import java.util.HashMap;
import java.util.Set;

public class Lobby {
    private int capacity;
    private int id;
    private User admin;
    private final HashMap<User, Color> players = new HashMap<>();
    private String mapId;

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

    public int getId() {
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

    public int getPlayersCount() {
        return players.size();
    }

    public Set<User> getPlayers() {
        return players.keySet();
    }
}
