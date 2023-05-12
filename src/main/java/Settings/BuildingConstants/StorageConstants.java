package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

import static model.enums.AssetType.Material.*;

public enum StorageConstants {
    STABLE(200, MapAssetType.STABLE, 400, 0,
            WOOD, 0, new ArrayList<>(List.of(CellType.STONE, CellType.SLATE, CellType.GRAVEL)), 20,
            4, List.of(HORSE)),
    STORE_HOUSE(200, MapAssetType.STORE_HOUSE, 0, 0,
            null, 0,
            new ArrayList<>(List.of(CellType.STONE, CellType.SLATE, CellType.GRAVEL)), 0,
            100, List.of(GRAIN, BEAR, FLOUR, WHEAT, WOOD, STONE, SPEAL, PICK, ROCKBLOCK, PITCH, WINE, IRON)),
    FOOD_STORAGE(200, MapAssetType.FOOD_STORAGE, 0, 0,
            WOOD, 0,
            new ArrayList<>(List.of(CellType.STONE, CellType.SLATE, CellType.GRAVEL)), 5, 100,
            List.of(MEAT, APPLE, BREAD, CHEESE)),
    ARMOURY(200, MapAssetType.ARMOURY, 0, 0,
            WOOD, 0,
            new ArrayList<>(List.of(CellType.STONE, CellType.SLATE, CellType.GRAVEL)), 5, 20,
            List.of(ARMOUR, MACE, SWORD, BOW, CROSS_BOW, SPEAR, PIKE));

    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;
    public final int totalCapacity;
    public final List<Material> storage;

    StorageConstants(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int totalCapacity, List<Material> storage) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
        this.totalCapacity = totalCapacity;
        this.storage = storage;
    }
}
