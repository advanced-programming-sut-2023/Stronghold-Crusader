package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;

public enum BuildingConstants {
    //Normal buildings
    HEADQUARTER(500, MapAssetType.HEADQUARTER, 0, 50, null, 0, 0, null),
    KILLING_PIT(Integer.MAX_VALUE, MapAssetType.KILLING_PIT, 0, 0, Material.WOOD, 6, 0, null),
    HOUSE(200, MapAssetType.HOUSE, 0, 8, Material.WOOD, 6, 0, null),
    CAGED_WARDOG(20, MapAssetType.CAGED_WARDOG, 100, 0, Material.WOOD, 10, 0, null),
    STAIRS(150, MapAssetType.STAIRS, 0, 0, Material.STONE, 10, 0, null),
    OX_TETHER(20, MapAssetType.OX_TETHER, 0, 0, Material.WOOD, 5, 0, null),

    //Walls
    SHORT_WALL(200, MapAssetType.SHORT_WALL, 0, 0, Material.STONE, 10, 0, null),
    WALL(400, MapAssetType.WALL, 0, 0, Material.STONE, 20, 0, null),

    //Entrance buildings
    BIG_GATE_HOUSE(300, MapAssetType.BIG_GATEHOUSE, 0, 10, Material.STONE, 20, 0, null),
    SMALL_GATE_HOUSE(300, MapAssetType.SMALL_GATEHOUSE, 0, 8, null, 0, 0, null),
    DRAW_BRIDGE(200, MapAssetType.DRAW_BRIDGE, 0, 0, Material.WOOD, 10, 0, null);


    public final int maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;

    BuildingConstants(int maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int neededMaterialAmount, int workerCount, ArrayList<CellType> buildingGroundType) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.neededMaterialAmount = neededMaterialAmount;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
    }
}
