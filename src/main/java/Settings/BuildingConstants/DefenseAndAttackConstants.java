package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum DefenseAndAttackConstants {
    LOOKOUT_TOWER(200, MapAssetType.LOOKOUT_TOWER, 0, 0,
            Material.STONE, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, 0, 0, 20, false),
    PERIMETER_TOWER(200, MapAssetType.PERIMETER_TOWER, 2, 3,
            Material.STONE, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, 0, 0, 20, false),
    DEFENSE_TOWER(300, MapAssetType.DEFENSE_TOWER, 2, 3,
            Material.STONE, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            15, 0, 0, 20, false),
    SQUARE_TOWER(300, MapAssetType.SQUARE_TOWER, 2, 3,
            Material.STONE, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            35, 0, 0, 20, true),
    CIRCULAR_TOWER(400, MapAssetType.CIRCULAR_TOWER, 2, 3,
            Material.STONE, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            40, 0, 0, 20, true)
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

    DefenseAndAttackConstants(double maxHitPoint, MapAssetType type, int cost,
                              int populationCapacity, Material neededMaterial,
                              int workerCount, ArrayList<CellType> buildingGroundType,
                              int neededMaterialAmount, int fireRange, int defendRange,
                              int troopCapacity, boolean siegeEquipmentAllowance) {
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
