package controller.MapControllers;


import model.Map.Map;
import model.Map.MapManager;
import model.Player;
import model.User;
import view.MapMenus.MapSelectMenu;
import view.enums.messages.MapSelectMessage;

import java.util.ArrayList;

public class MapSelectController {
    private User currentUser;
    private Map selectedMap;
    private ArrayList<Player> players;
    private boolean isMapModifiable;

    public MapSelectController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        MapSelectMenu mapSelectMenu = new MapSelectMenu(this);
        while (true) {
            switch (mapSelectMenu.run()) {

            }
        }
    }

    public String getMapsList() {
        ArrayList<ArrayList<String>> maps = MapManager.getMapList();
        String output = "";
        for (ArrayList<String> map : maps) {
            output += "Map id: " + map.get(0) + "Map name : " + map.get(1) + " Number of players : " + map.get(2);
            output += "\n";
        }
        return output;
    }

    public MapSelectMessage selectMap(String mapId) {
        if (!MapManager.isMapIDValid(mapId)) return MapSelectMessage.INVALID_MAP_ID;
        selectedMap = MapManager.load(mapId);
        return MapSelectMessage.MAP_SELECT_SUCCESS;
    }

    public String numberOfPlayers() {
        if (selectedMap == null) return MapSelectMessage.MAP_NOT_SELECTED.getMessage();
        return String.valueOf(selectedMap.getPlayerCount());
    }

    public MapSelectMessage addPlayer(User user) {
        Player newPlayer = new Player(user.getUsername(), user.getNickname(), user.getSlogan());
        return MapSelectMessage.PLAYER_ADD_SUCCESS;
    }

}
