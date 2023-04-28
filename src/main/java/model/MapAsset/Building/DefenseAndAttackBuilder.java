package model.MapAsset.Building;

import Settings.BuildingConstants.DefenseAndAttackConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class DefenseAndAttackBuilder {

    public static DefenseAndAttackBuilding createBuilding(Vector2D coordinate, Player owner, DefenseAndAttackConstants buildingType) {
        DefenseAndAttackBuilding building = new DefenseAndAttackBuilding(coordinate, owner, buildingType.getType());
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