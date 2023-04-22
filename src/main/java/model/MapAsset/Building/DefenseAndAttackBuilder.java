package model.MapAsset.Building;

import Settings.BuildingConstants.DefenseAndAttackConstants;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class DefenseAndAttackBuilder {

    public static DefenseAndAttackBuilding createBuilding(Vector2D coordinate, Player owner, MapAsset type, DefenseAndAttackConstants buildingType) {
        DefenseAndAttackBuilding building = new DefenseAndAttackBuilding(coordinate, owner, type);
        setAttrs(building, buildingType);
        return building;
    }

    private static void setAttrs(DefenseAndAttackBuilding building, DefenseAndAttackConstants buildingType) {
        building.setDefendRange(buildingType.getDefendRange());
        building.setMaxHitPoint(buildingType.getHitpoint());
        building.setFireRange(buildingType.getFireRange());
        building.setSiegeEquipmentAllowance(buildingType.isSiegeEquipmentAllowance());
        building.setTroopCapacity(buildingType.getTroopCapacity());
    }
}