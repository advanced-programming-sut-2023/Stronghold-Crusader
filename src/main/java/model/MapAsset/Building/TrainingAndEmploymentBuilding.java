package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;

public class TrainingAndEmploymentBuilding extends Building {
    private final ArrayList<MapAssetType> peopleType;
    private final int popularityChangeRate;

    public TrainingAndEmploymentBuilding(TrainingAndEmploymentBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.peopleType = reference.peopleType;
        this.popularityChangeRate = reference.popularityChangeRate;
    }

    public TrainingAndEmploymentBuilding(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, ArrayList<MapAssetType> peopleType, int popularityChangeRate) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
        this.peopleType = peopleType;
        this.popularityChangeRate = popularityChangeRate;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", peopleType=" + peopleType +
                ", popularityChangeRate=" + popularityChangeRate;
    }
}
