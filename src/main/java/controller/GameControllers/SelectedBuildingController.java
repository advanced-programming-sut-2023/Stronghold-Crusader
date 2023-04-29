package controller.GameControllers;

import model.Governance;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.MapAsset;
import model.enums.Material;
import view.GameMenus.SelectedAssetMenu;
import view.enums.messages.SelectedBuildingMessage;

public class SelectedBuildingController {
    private final Building building;
    private final Governance governance;
    private final Map map;
    private final int x,y;
    public SelectedBuildingController(Building building, Governance governance, Map map, int x, int y){
        this.building = building;
        this.governance = governance;
        this.map = map;
        this.x = x;
        this.y = y;
    }

    public void run(){
        SelectedAssetMenu selectedAssetMenu = new SelectedAssetMenu(this);
    }
    public SelectedBuildingMessage repair(){
        if (governance.getStorage().getMaterials().get(Material.STONE) < materialNeededForRepair())
            return SelectedBuildingMessage.STONE_NEEDED;
        if (isThereEnemy())
            return SelectedBuildingMessage.ENEMY_EXIST;
        return SelectedBuildingMessage.SUCCESS_REPAIR;
    }

    private int materialNeededForRepair(){
        return (int) ((building.getMaxHitPoint() - building.getHitPoint())/ building.getMaxHitPoint()
                        * building.getNumberOfMaterialNeeded());
    }

    //TODO Find nearby Cells
    private  boolean isThereEnemy(){
        for (MapAsset mapAsset: map.getCell(x,y).getAllObjects()) {
            if (mapAsset.getOwner().equals(governance.getPlayer())) return true;
        }
        return false;
    }
}
