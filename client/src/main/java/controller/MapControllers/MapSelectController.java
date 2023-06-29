package controller.MapControllers;


import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import model.Game.Game;
import model.Map.Map;
import model.Map.MapManager;
import model.User.Player;
import model.Stronghold;
import model.User.User;
import model.enums.User.Color;
import view.GameMenus.GraphicGameMenu;
import view.Main;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;
import view.enums.messages.MapMessage.MapSelectMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class MapSelectController {
    private final User currentUser;
    private Map selectedMap;
    private HashMap<Color, Player> players;
    private boolean isMapModifiable;

    public MapSelectController(User currentUser) {
        this.currentUser = currentUser;
        this.players = new HashMap<>();
    }

    public String getMapsList() {
        ArrayList<ArrayList<String>> maps = MapManager.getMapList();
        StringBuilder output = new StringBuilder();
        for (ArrayList<String> map : maps) {
            output.append("Map id:").append(map.get(0)).append(", Map name:").append(map.get(1)).append(", Number of players:").append(map.get(2));
            output.append("\n");
        }
        return output.toString();
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
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        Color color = Color.getColorWithSizeCheck(colorName, selectedMap.getPlayerCount());
        User user = Stronghold.getInstance().getUser(username);
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

    public MapSelectMessage startGame() throws Exception {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED;
        if (players.size() < selectedMap.getPlayerCount()) return MapSelectMessage.NOT_ENOUGH_PLAYERS;
        Game newGame = new Game(selectedMap, players, isMapModifiable);
        GameController controller = new GameController(currentUser, newGame);
        GraphicGameMenu menu = new GraphicGameMenu();
        GraphicGameMenu.setGameController(controller);
        GraphicGameMenu.setGraphicsController(new GraphicsController(controller, newGame, menu));
        GraphicBuildingPlacementMenu.setController(new BuildingPlacementController(newGame.getCurrentPlayer(), newGame.getMap(), isMapModifiable));
        menu.start(Main.mainStage);
        return MapSelectMessage.GAME_CREATION_SUCCESS;
    }

    public Map getSelectedMap() {
        return selectedMap;
    }

    public HashMap<Color, Player> getPlayers() {
        return players;
    }
}
