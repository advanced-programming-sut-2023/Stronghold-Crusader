package controller.MapControllers;

import model.Map.Map;
import model.MapAsset.MapAsset;
import model.User;
import utils.Vector2D;
import view.MapMenus.BuildingPlacementMenu;
import view.enums.messages.MapMakerMessage;

public class BuildingPlacementController {
    private User currentUser;
    private Map map;

    public BuildingPlacementController(User currentUser, Map map) {
        this.currentUser = currentUser;
        this.map = map;
    }

    public void run() {
        BuildingPlacementMenu menu = new BuildingPlacementMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    public MapMakerMessage dropBuilding(int x, int y, MapAsset type) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        // if map asset type is invalid return invalid

        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropUnit(int x, int y, MapAsset type) {
        return null;
    }

}

