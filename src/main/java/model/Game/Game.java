package model.Game;

import model.Map.Map;
import model.MapAsset.MapAsset;
import model.User.Player;
import model.enums.User.Color;
import utils.Vector2D;

import java.util.Collection;
import java.util.HashMap;

public class Game {
    private final HashMap<Color, Player> players;
    private final boolean isEditableMode;
    private final Map map;
    private Player currentPlayer;
    private int turnCounter;

    public Game(Map map, HashMap<Color, Player> players, boolean isEditableMode) {
        this.map = map;
        this.isEditableMode = isEditableMode;
        this.players = players;
        initializeColors();
    }

    public void nextPlayer() {
    }

    public void nextTurn() {
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    private void initializeColors() {
        for (java.util.Map.Entry<Color, Player> colorPlayerEntry : players.entrySet()) {
            int colorIndex = colorPlayerEntry.getKey().ordinal();
            Vector2D hqCoord = getMap().getHeadQuarterCoordinate(colorIndex);
            MapAsset headQuarter = getMap().getCell(hqCoord).getAllAssets().get(0);
            headQuarter.setOwner(colorPlayerEntry.getValue());
            colorPlayerEntry.getValue().getGovernance().addAsset(headQuarter);
            Vector2D storeCoord = getMap().getStoreHouseCoordinate(colorIndex);
            MapAsset storeHouse = getMap().getCell(storeCoord).getAllAssets().get(0);
            storeHouse.setOwner(colorPlayerEntry.getValue());
            colorPlayerEntry.getValue().getGovernance().addAsset(storeHouse);
        }
    }
}
