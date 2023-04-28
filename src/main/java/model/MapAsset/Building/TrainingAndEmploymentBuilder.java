package model.MapAsset.Building;

import Settings.BuildingConstants.TrainingAndEmploymentConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class TrainingAndEmploymentBuilder {
    public static TrainingAndEmploymentBuilding createBuilding(Vector2D coordinate, Player owner, TrainingAndEmploymentConstants buildingType) {
        TrainingAndEmploymentBuilding building = new TrainingAndEmploymentBuilding(coordinate, owner, buildingType.getType());
        setAttrs(building, buildingType);
        return building;
    }


    private static void setAttrs(TrainingAndEmploymentBuilding building, TrainingAndEmploymentConstants buildingType) {
        building.setPeopleType(buildingType.getPeopleType());
        building.setPopularityChangeRate(buildingType.getPopularityChangeRate());
        building.setMaxHitPoint(buildingType.getHitpoint());
    }
}
