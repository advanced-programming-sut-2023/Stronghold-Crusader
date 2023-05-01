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
    private User currentUser;
    private Map selectedMap;
    private String selectedMapId;
    private HashMap<Color,Player> players;
    private boolean isMapModifiable;
    private Game newGame;

    public MapSelectController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        MapSelectMenu mapSelectMenu = new MapSelectMenu(this);
        while (true) {
            if (mapSelectMenu.run().equals("newGame")) {
                GameController gameController = new GameController(currentUser, newGame);
                if(gameController.run().equals("endGame")) return;
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

    public MapSelectMessage selectMap(String mapId) {
        if (!MapManager.isMapIDValid(mapId)) return MapSelectMessage.INVALID_MAP_ID;
        selectedMap = MapManager.load(mapId);
        selectedMapId = mapId;
        return MapSelectMessage.MAP_SELECT_SUCCESS;
    }

    public String numberOfPlayers() {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED.getMessage();
        return String.valueOf(selectedMap.getPlayerCount());
    }

    public MapSelectMessage addPlayer(String username, String colorName) {
        Color color = Color.getColor(colorName);
        User user = Stronghold.getInstance().getUser(username);
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        if (players.size() == selectedMap.getPlayerCount()) return MapSelectMessage.PLAYER_COUNT_EXCEEDED;
        if (user == null) return MapSelectMessage.USERNAME_INVALID;
        if (color == null) return MapSelectMessage.INVALID_COLOR;
        Player newPlayer = new Player(user.getUsername(), user.getNickname(), user.getSlogan());
        players.put(color,newPlayer);
        return MapSelectMessage.PLAYER_ADD_SUCCESS;
    }


    public MapSelectMessage setMapModifiability(String access) {
        if (!FormatValidation.isFormatValid(access, FormatValidation.BOOLEAN_ANSWER))
            return MapSelectMessage.INVALID_MODIFIABILITY;
        if (access.equalsIgnoreCase("t")) isMapModifiable = true;
        if (access.equalsIgnoreCase("f")) isMapModifiable = false;
        return MapSelectMessage.MODIFIABILITY_SUCCESS;
    }

    public MapSelectMessage startGame() {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        if (players.size() < selectedMap.getPlayerCount()) return MapSelectMessage.NOT_ENOUGH_PLAYERS;
        newGame = new Game(selectedMapId, players, isMapModifiable);
        return MapSelectMessage.GAME_CREATION_SUCCESS;
    }
}
