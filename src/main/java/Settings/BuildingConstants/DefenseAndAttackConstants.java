package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;

public enum DefenseAndAttackConstants {
    // TODO fill this
//    LOOKOUT_TOWER(1, MapAssetType.LOOKOUT_TOWER, 0, 0, false, MapAssetType.LOOKOUT_TOWER),
//    PERIMETER_TOWER(1, 1, 2, 3, false, MapAssetType.PERIMETER_TOWER),
//    DEFENSE_TOWER(1, 1, 2, 3, false, MapAssetType.DEFENSE_TOWER),
//    SQUARE_TOWER(1, 1, 2, 3, false, MapAssetType.SQUARE_TOWER),
//    CIRCULAR_TOWER(1, 1, 2, 3, false, MapAssetType.CIRCULAR_TOWER)
    ;
    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;
    public final int fireRange;
    public final int defendRange;
    public final int troopCapacity;
    public final boolean siegeEquipmentAllowance;

    DefenseAndAttackConstants(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int fireRange, int defendRange, int troopCapacity, boolean siegeEquipmentAllowance) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.troopCapacity = troopCapacity;
        this.siegeEquipmentAllowance = siegeEquipmentAllowance;
    }
}
