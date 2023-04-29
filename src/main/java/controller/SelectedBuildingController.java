package controller;

import model.Governance;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.enums.Material;
import view.GameMenus.SelectedBuildingMenu;
import view.enums.messages.SelectedBuildingMessage;

public class SelectedBuildingController {
    private final Building building;
    private final Governance governance;
    private final Map map;
    public SelectedBuildingController(Building building, Governance governance, Map map){
        this.building = building;
        this.governance = governance;
        this.map = map;
    }

    public void run(){
        SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu(this);
    }
    public SelectedBuildingMessage repair(){
        if (governance.getStorage().getMaterials().get(Material.STONE) < stoneNeededForRepair())
            return SelectedBuildingMessage.STONE_NEEDED;
        //if ()
        return null;
    }

    private int stoneNeededForRepair(){
        return (int) ((building.getMaxHitPoint() - building.getHitPoint())/100);
    }
}
