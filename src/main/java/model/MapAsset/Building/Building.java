package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.CellType;
import model.enums.AssetType.Material;
import utils.Vector2D;

import java.util.ArrayList;

//TODO complete this class
public class Building extends MapAsset {
    private Material neededMaterial;
    private int neededMaterialAmount;
    private final int cost;
    private int workerNumber;
    private final ArrayList<CellType> buildingGroundType;

    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.cost = reference.cost;
        this.buildingGroundType = reference.buildingGroundType;
    }

    public int getNumberOfMaterialNeeded() {
        return neededMaterialAmount;
    }

    public Material getNeededMaterial() {
        return neededMaterial;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setNeededMaterial(Material neededMaterial) {
        this.neededMaterial = neededMaterial;
    }

    public void repair() {
        hitPoint = maxHitPoint;
    }
    public boolean isCellTypeValid(CellType celltype){
        return buildingGroundType.contains(celltype);
    }
}
