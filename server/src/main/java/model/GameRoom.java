package model;

import java.awt.*;
import java.util.HashMap;

public class GameRoom {
    private final User admin;
    private final HashMap<Color, User> players = new HashMap<>();
    private final String mapID;

    public GameRoom(User admin, Color color, String mapID) {
        this.admin = admin;
        this.mapID = mapID;
        players.put(color, admin);
    }

    public boolean isColorPicked(Color color) {
        return (players.get(color) != null);
    }

}
