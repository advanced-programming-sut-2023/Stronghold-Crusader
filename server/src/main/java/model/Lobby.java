package model;

import database.Database;

import java.util.*;

public class Lobby {
    private LobbyStatus lobbyStatus;
    private int capacity;
    private String id;
    private User admin;
    private final HashMap<String, Color> players = new HashMap<>();
    private String mapId;
    private transient Timer expireTimer;

    public boolean isColorPicked(Color color) {
        return players.containsValue(color);
    }

    public void addPlayer(User player, Color color) {
        players.put(player.getUsername(), color);
        updateExpireTime();
    }

    public void removePlayer(User player) {
        players.remove(player.getUsername());
        updateExpireTime();
    }

    public void setAdmin(User admin) {
        this.admin = admin;
        updateExpireTime();
    }

    public void setLobbyStatus(LobbyStatus lobbyStatus) {
        this.lobbyStatus = lobbyStatus;
        updateExpireTime();
    }

    public void updateExpireTime() {
        if (expireTimer != null)
            expireTimer.cancel();
        expireTimer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Database.getInstance().removeLobby(id);
                System.out.println("Removed lobby:" + id);
            }
        };
        expireTimer.schedule(timerTask, 60000);
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
}
