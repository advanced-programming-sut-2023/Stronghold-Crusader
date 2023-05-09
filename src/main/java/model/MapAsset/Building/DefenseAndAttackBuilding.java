package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;

public class DefenseAndAttackBuilding extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int troopCapacity;
    private final boolean siegeEquipmentAllowance;
    private int currentTroopCount;

    public DefenseAndAttackBuilding(DefenseAndAttackBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.fireRange = reference.fireRange;
        this.defendRange = reference.defendRange;
        this.troopCapacity = reference.troopCapacity;
        this.siegeEquipmentAllowance = reference.siegeEquipmentAllowance;
        currentTroopCount = 0;
    }

    public DefenseAndAttackBuilding(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int fireRange, int defendRange, int troopCapacity, boolean siegeEquipmentAllowance) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.troopCapacity = troopCapacity;
        this.siegeEquipmentAllowance = siegeEquipmentAllowance;
    }

    public void changeTroopCount(int offset) {
        this.currentTroopCount += offset;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", fire range=" + fireRange +
                ", defend range=" + defendRange +
                ", capacity=" + currentTroopCount + '/' + troopCapacity +
                ", siege allowance=" + siegeEquipmentAllowance;
    }
}
