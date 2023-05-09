package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.CellType;
import model.enums.AssetType.Material;
import utils.Vector2D;

import java.util.ArrayList;

//TODO complete this class
public class Building extends MapAsset {
    private final int populationCapacity;
    private final Material neededMaterial;
    private final int workerCount;
    private final ArrayList<CellType> buildingGroundType;
    private final int neededMaterialAmount;

    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.buildingGroundType = reference.buildingGroundType;
        this.neededMaterial = reference.neededMaterial;
        this.workerCount = reference.workerCount;
        this.populationCapacity = reference.populationCapacity;
        this.neededMaterialAmount = reference.neededMaterialAmount;
    }

    public Building(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount) {
        super(maxHitPoint, type, cost);
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
    }

    public int getNumberOfMaterialNeeded() {
        return neededMaterialAmount;
    }

    public int getPopulationCapacity() {
        return populationCapacity;
    }

    public Material getNeededMaterial() {
        return neededMaterial;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void repair() {
        hitPoint = maxHitPoint;
    }

    public boolean isCellTypeValid(CellType celltype) {
        return buildingGroundType.contains(celltype);
    }
}
