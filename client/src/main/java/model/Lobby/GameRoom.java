package model.Lobby;

import model.User.Player;
import model.User.User;
import model.enums.User.Color;

import java.util.HashMap;

public class GameRoom {
    private final User admin;
    private final HashMap<Color, Player> players = new HashMap<>();
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
