package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private HashMap<String, Player> players;
    private Player currentPlayer;
    private boolean isEditableMode;
    private int turnCounter;
    private int time;
    private Map map;

    public Game(String mapFileName, ArrayList<Player> players, boolean isEditableMode) {
        map = new Map(mapFileName);
        //make players from the users
    }

    public void assignColor(Player player, Color color) {
        // assign players color field
        // assign owner for governments base
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

    public int getTime() {
        return time;
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
}
