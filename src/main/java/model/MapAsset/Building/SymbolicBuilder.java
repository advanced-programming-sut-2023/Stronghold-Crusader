package model.MapAsset.Building;

import Settings.BuildingConstants.SymbolicConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class SymbolicBuilder {
    public static SymbolicBuilding createBuilding(Vector2D coordinate, Player owner, SymbolicConstants buildingType) {
        SymbolicBuilding building = new SymbolicBuilding(coordinate, owner, buildingType.getType());
        setAttrs(building, buildingType);
        return building;
    }

    private static void setAttrs(SymbolicBuilding building, SymbolicConstants buildingType) {
        building.setMaxHitPoint(buildingType.getHitpoint());
        building.setChangeType(buildingType.getChangeType());
        building.setCapacityChangeRate(buildingType.getChangeRate());
    }
}
