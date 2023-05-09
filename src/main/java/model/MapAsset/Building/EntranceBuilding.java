package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;

public class EntranceBuilding extends Building {
    private boolean isOpen;
    private boolean hasFlag;

    public EntranceBuilding(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        isOpen = true;
        hasFlag = false;
    }

    public EntranceBuilding(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public void setFlag(boolean flag) {
        hasFlag = flag;
    }
}
