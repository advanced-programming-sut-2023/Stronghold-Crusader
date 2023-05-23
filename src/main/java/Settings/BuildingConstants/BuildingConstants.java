package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum BuildingConstants {
    HEADQUARTER(500, MapAssetType.HEADQUARTER, 0, 50,
            null, 0, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    STORE(200, MapAssetType.STORE, 0, 0,
            Material.WOOD, 5, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    KILLING_PIT(Integer.MAX_VALUE, MapAssetType.KILLING_PIT, 0, 0,
            Material.WOOD, 6, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    HOUSE(200, MapAssetType.HOUSE, 0, 8,
            Material.WOOD, 6, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    CAGED_WARDOG(20, MapAssetType.CAGED_WARDOG, 100, 0,
            Material.WOOD, 10, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    STAIRS(150, MapAssetType.STAIRS, 0, 0,
            Material.STONE, 10, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    OX_TETHER(20, MapAssetType.OX_TETHER, 0, 0,
            Material.WOOD, 5, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),

    //Walls
    SHORT_WALL(200, MapAssetType.SHORT_WALL, 0, 0,
            Material.STONE, 10, 0,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW))),
    WALL(400, MapAssetType.WALL, 0, 0, Material.STONE,
            20, 0,
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

    BuildingConstants(int maxHitPoint, MapAssetType type, int cost,
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
