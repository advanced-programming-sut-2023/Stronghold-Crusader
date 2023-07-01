package model;

import database.Database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lobby {
    private LobbyStatus lobbyStatus;
    private int capacity;
    private String id;
    private User admin;
    private final HashMap<String, Color> players = new HashMap<>();
    private String mapId;

    public boolean isColorPicked(Color color) {
        return players.containsValue(color);
    }

    public void addPlayer(User player, Color color) {
        players.put(player.getUsername(), color);
    }

    public void removePlayer(User player) {
        players.remove(player.getUsername());
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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

    public int getPlayersCount() {
        return players.size();
    }

    public Set<User> getPlayers() {
        Set<User> users = new HashSet<>();
        for (String username : players.keySet())
            users.add(Database.getInstance().getUser(username));
        return users;
    }

    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }

    public void setLobbyStatus(LobbyStatus lobbyStatus) {
        this.lobbyStatus = lobbyStatus;
    }
}
