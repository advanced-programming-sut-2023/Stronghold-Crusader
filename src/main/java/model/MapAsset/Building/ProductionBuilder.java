package model.MapAsset.Building;

import Settings.BuildingConstants.DefenseAndAttackConstants;
import Settings.BuildingConstants.ProductionConstants;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class ProductionBuilder {
    public static ProductionBuilding createBuilding(Vector2D coordinate, Player owner, MapAsset type, ProductionConstants buildingType) {
        ProductionBuilding building = new ProductionBuilding(coordinate, owner, type);
        setAttrs(building, buildingType);
        return building;
    }

    private static void setAttrs(ProductionBuilding building, ProductionConstants buildingType) {
        building.setProductionCapacity(buildingType.getProductionMaxCapacity());
        building.setMaxHitPoint(buildingType.getHitpoint());
        building.setProducingMaterial(buildingType.getProducingMaterial());
        building.setUsingMaterial(buildingType.getUsingMaterial());
        building.setRateOfProcess(buildingType.getProcessRate());
        building.setRateOfUsage(buildingType.getUsingRate());
        building.setRateOfProduction(buildingType.getProducingRate());
    }
}
