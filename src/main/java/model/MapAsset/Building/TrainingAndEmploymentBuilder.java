package model.MapAsset.Building;

import Settings.BuildingConstants.TrainingAndEmploymentConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class TrainingAndEmploymentBuilder {
    public static TrainingAndEmploymentBuilding createBuilding(Vector2D coordinate, Player owner, MapAssetType type, TrainingAndEmploymentConstants buildingType) {
        TrainingAndEmploymentBuilding building = new TrainingAndEmploymentBuilding(coordinate, owner, type);
        setAttrs(building, buildingType);
        return building;
    }


    //TODO change name
    private static void setAttrs(TrainingAndEmploymentBuilding building, TrainingAndEmploymentConstants buildingType) {
        building.setPeopleType(buildingType.getPeopleType());
        building.setPopularityChangeRate(buildingType.getPopularityChangeRate());
        building.setMaxHitPoint(buildingType.getHitpoint());
    }
}
