package controller.MapControllers;


import controller.GameControllers.GameController;
import model.Game;
import model.Map.Map;
import model.Map.MapManager;
import model.Player;
import model.Stronghold;
import model.User;
import model.enums.Color;
import utils.FormatValidation;
import view.MapMenus.MapSelectMenu;
import view.enums.messages.MapMessage.MapSelectMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class MapSelectController {
    private final User currentUser;
    private Map selectedMap;
    private HashMap<Color, Player> players;
    private boolean isMapModifiable;
    private Game newGame;

    public MapSelectController(User currentUser) {
        this.currentUser = currentUser;
        this.players = new HashMap<>();
    }

    public void run() {
        MapSelectMenu mapSelectMenu = new MapSelectMenu(this);
        while (true) {
            if (mapSelectMenu.run().equals("startGame")) {
                GameController gameController = new GameController(currentUser, newGame);
                if (gameController.run().equals("endGame")) return;
            }
        }
    }

    public String getMapsList() {
        ArrayList<ArrayList<String>> maps = MapManager.getMapList();
        String output = "";
        for (ArrayList<String> map : maps) {
            output += "Map id:" + map.get(0) + ", Map name:" + map.get(1) + ", Number of players:" + map.get(2);
            output += "\n";
        }
        return output;
    }

    public MapSelectMessage selectMap(String mapId, boolean isMapModifiable) {
        if (!MapManager.isMapIDValid(mapId)) return MapSelectMessage.INVALID_MAP_ID;
        this.isMapModifiable = isMapModifiable;
        selectedMap = MapManager.load(mapId);
        players = new HashMap<>();
        return MapSelectMessage.MAP_SELECT_SUCCESS;
    }

    public String numberOfPlayers() {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED.getMessage();
        return String.valueOf(players.size()) + '/' + selectedMap.getPlayerCount();
    }

    public MapSelectMessage addPlayer(String username, String colorName) {
        Color color = Color.getColor(colorName);
        User user = Stronghold.getInstance().getUser(username);
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        if (players.size() == selectedMap.getPlayerCount()) return MapSelectMessage.PLAYER_COUNT_EXCEEDED;
        if (user == null) return MapSelectMessage.USERNAME_INVALID;
        if (color == null) return MapSelectMessage.INVALID_COLOR;
        if (players.get(color) != null)
            return MapSelectMessage.PICKED_COLOR;
        for (Player player : players.values()) {
            if (user.getUsername().equals(player.getUsername()))
                return MapSelectMessage.PLAYER_EXISTS;
        }
        Player newPlayer = new Player(user);
        players.put(color, newPlayer);
        return MapSelectMessage.PLAYER_ADD_SUCCESS;
    }

    public MapSelectMessage startGame() {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        if (players.size() < selectedMap.getPlayerCount()) return MapSelectMessage.NOT_ENOUGH_PLAYERS;
        newGame = new Game(selectedMap, players, isMapModifiable);
        return MapSelectMessage.GAME_CREATION_SUCCESS;
    }
}
