package model.MapAsset.Building;

import Settings.BuildingConstants.ProductionConstants;
import Settings.BuildingConstants.SymbolicConstants;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class SymbolicBuilder {
    public static SymbolicBuilding createBuilding(Vector2D coordinate, Player owner, MapAsset type, SymbolicConstants buildingType) {
        SymbolicBuilding building = new SymbolicBuilding(coordinate, owner, type);
        setAttrs(building, buildingType);
        return building;
    }

    private static void setAttrs(SymbolicBuilding building, SymbolicConstants buildingType) {
        building.setMaxHitPoint(buildingType.getHitpoint());
        building.setChangeType(buildingType.getChangeType());
        building.setCapacityChangeRate(buildingType.getChangeRate());
    }
}
