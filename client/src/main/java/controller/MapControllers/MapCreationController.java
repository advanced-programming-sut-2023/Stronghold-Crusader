package controller.MapControllers;

import controller.GameControllers.GraphicsController;
import model.Game.Game;
import model.Map.Map;
import utils.Vector2D;
import view.Main;
import view.MapMenus.MapCreationMenu.MapCreationGraphicController;
import view.MapMenus.MapCreationMenu.MapCreationMenu;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;
import view.enums.messages.MapMessage.MapCreationMessage;

import java.util.HashMap;

public class MapCreationController {
    private Map map;

    public MapCreationMessage initializeMap(String mapId, int mapY, int mapX, int numberOfPlayers){
        // TODO : Check for repetitive map name
        if (mapId.equals("")) return MapCreationMessage.ENTER_ID;
        else if (mapY == -1 || mapY == -1) return MapCreationMessage.ENTER_SIZE;
        else if (numberOfPlayers == -1) return MapCreationMessage.ENTER_PLAYER_NUMER;
        else {
            map = new Map(new Vector2D(mapX, mapY), mapId);
            map.setPlayerCount(numberOfPlayers);
            return MapCreationMessage.MAP_INITIALIZE_SUCCESS;
        }
    }

    public void goToMapCreationMenu() throws Exception {
        MapCreationMenu mapCreationMenu = new MapCreationMenu();
        Game game = new Game(map, new HashMap<>(), true, "");
        GraphicsController graphicsController = new GraphicsController(game);
        MapCreationGraphicController.setGraphicsController(graphicsController);
        MapCreationGraphicController.setController(this);
        GraphicBuildingPlacementMenu.setController(new BuildingPlacementController(map, true));
        mapCreationMenu.start(Main.mainStage);
    }

    public Map getMap() {
        return map;
    }

}
