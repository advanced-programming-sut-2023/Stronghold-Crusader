package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum EntranceBuildingConstants {
    //Entrance buildings
    BIG_GATE_HOUSE(300, MapAssetType.BIG_GATEHOUSE, 0, 10,
            Material.STONE, 20, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    SMALL_GATE_HOUSE(300, MapAssetType.SMALL_GATEHOUSE, 0, 8,
            null, 0, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    DRAW_BRIDGE(200, MapAssetType.DRAW_BRIDGE, 0, 0,
            Material.WOOD, 10, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)));


    public final int maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;

    EntranceBuildingConstants(int maxHitPoint, MapAssetType type, int cost,
                              int populationCapacity, Material neededMaterial,
                              int neededMaterialAmount, int workerCount,
                              ArrayList<CellType> buildingGroundType) {
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
