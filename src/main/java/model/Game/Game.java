package model.Game;

import model.Map.Map;
import model.User.Player;
import model.enums.User.Color;

import java.util.Collection;
import java.util.HashMap;

public class Game {
    private final HashMap<String, Player> players;
    private Player currentPlayer;
    private final boolean isEditableMode;
    private int turnCounter;
    private final Map map;

    public Game(Map map, HashMap<Color,Player> players, boolean isEditableMode) {
        this.map = map;
        this.isEditableMode = isEditableMode;
        this.players = new HashMap<>();
        for(Player p : players.values()){
            this.players.put(p.getUsername(), p);
        }
    }

    public void nextPlayer() {
    }

    public void nextTurn() {
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayerByUsername(String username) {
        return null;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public Map getMap() {
        return map;
    }

    public Enum getGameState() {
        return null;
    }

    public Collection<Player> getPlayers() {
        return players.values();
    }
}
