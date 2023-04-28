package model.MapAsset.Building;

import Settings.BuildingConstants.ProductionConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class ProductionBuilder {
    public static ProductionBuilding createBuilding(Vector2D coordinate, Player owner, ProductionConstants buildingType) {
        ProductionBuilding building = new ProductionBuilding(coordinate, owner, buildingType.getType());
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
