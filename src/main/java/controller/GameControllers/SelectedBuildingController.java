package controller.GameControllers;

import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;
import view.GameMenus.SelectedAssetMenu;
import view.enums.messages.SelectedBuildingMessage;

public class SelectedBuildingController {
    private final Building building;
    private final Player player;
    private final Map map;
    private final int x, y;
    private final Vector2D coordinate;

    public SelectedBuildingController(Building building, Player player, Map map, int x, int y) {
        this.building = building;
        this.player = player;
        this.map = map;
        this.x = x;
        this.y = y;
        coordinate = new Vector2D(x, y);
    }

    public void run() {
        SelectedAssetMenu selectedAssetMenu = new SelectedAssetMenu(this);
        selectedAssetMenu.run();
    }

    public SelectedBuildingMessage repair() {
        if (player.governance.getStorage().getMaterials().get(building.getNeededMaterial()) < materialNeededForRepair())
            return SelectedBuildingMessage.MATERIAL_NEEDED;
        if (isThereEnemy())
            return SelectedBuildingMessage.ENEMY_EXIST;
        player.governance.getStorage().reduceMaterial(building.getNeededMaterial(), materialNeededForRepair());
        building.repair();
        return SelectedBuildingMessage.SUCCESS_REPAIR;
    }

    public String showInfo() {
        return building.toString();
    }

    public SelectedBuildingMessage deleteBuilding() {
        if (map.getCell(coordinate).isThereUnit())
            return SelectedBuildingMessage.NOT_ALLOWED_TO_DELETE;
        //TODO if Storage ...
        map.getCell(coordinate).removeMapAsset(building);
        player.governance.removeBuilding(building);
        return SelectedBuildingMessage.DELETED_BUILDING;
    }

    private int materialNeededForRepair() {
        return (int) ((building.getMaxHitPoint() - building.getHitPoint()) / building.getMaxHitPoint()
                * building.getNumberOfMaterialNeeded());
    }

    //TODO Find nearby Cells
    private boolean isThereEnemy() {
        for (MapAsset mapAsset : map.getCell(coordinate).getAllAssets()) {
            if (!mapAsset.getOwner().equals(player)) return true;
        }
        return false;
    }
}
