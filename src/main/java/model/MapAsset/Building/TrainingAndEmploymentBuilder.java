package model.MapAsset.Building;

import Settings.BuildingConstants.TrainingAndEmploymentConstants;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class TrainingAndEmploymentBuilder {
    public static TrainingAndEmploymentBuilding createBuilding(Vector2D coordinate, Player owner, MapAsset type, TrainingAndEmploymentConstants buildingType) {
        TrainingAndEmploymentBuilding building = new TrainingAndEmploymentBuilding(coordinate, owner, type);
        setAttrs(building, buildingType);
        return building;
    }

    private static void setAttrs(TrainingAndEmploymentBuilding building, TrainingAndEmploymentConstants buildingType) {
        building.setPeopleType(buildingType.getPeopleType());
        building.setPopularityChangeRate(buildingType.getPopularityChangeRate());
        building.setMaxHitPoint(buildingType.getHitpoint());
    }
}
