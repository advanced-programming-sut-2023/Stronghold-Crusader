package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum ProductionConstants {
    BEAR_FACTORY(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1 , new ArrayList<>(List.of(20)), 0),
    BAKERY(100, MapAssetType.BAKERY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.FLOUR)),
            new ArrayList<>(List.of(Material.BREAD)),
            new ArrayList<>(List.of(5)), 1, new ArrayList<>(List.of(30)), 0),
    WHEAT_FIELD(100, MapAssetType.WHEAT_FIELD,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            15, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.WHEAT)),
            new ArrayList<>(List.of(50)), 1, new ArrayList<>(List.of(0)), 0),
    GRAIN_FIELD(100, MapAssetType.GRAIN_FIELD,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            15, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(50)), 1, new ArrayList<>(List.of(0)), 0),
    DIARY_FACTORY(100, MapAssetType.DIARY_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.CHEESE, Material.LEATHER_WEST)),
            new ArrayList<>(List.of(10, 1)), 1, new ArrayList<>(List.of(0)), 0),
    APPLE_GARDEN(100, MapAssetType.APPLE_GARDEN,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            5, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(30)), 1, new ArrayList<>(List.of(0)), 0),
    POLTURNER(100, MapAssetType.POLTURNER,100, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.WOOD)),
            new ArrayList<>(List.of(Material.BOW, Material.CROSS_BOW)),
            new ArrayList<>(List.of(5, 5)), 1, new ArrayList<>(List.of(50)), 0),
    FLETCHER(100, MapAssetType.FLETCHER,100, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.WOOD)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1, new ArrayList<>(List.of(50)), 0),
    BLACKSMITH(100, MapAssetType.BLACKSMITH,100, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.IRON)),
            new ArrayList<>(List.of(Material.SWORD, Material.MACE)),
            new ArrayList<>(List.of(5, 5)), 1, new ArrayList<>(List.of(70)), 0),
    ARMOURER(100, MapAssetType.ARMOURER,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.IRON)),
            new ArrayList<>(List.of(Material.ARMOUR)),
            new ArrayList<>(List.of(5)), 1, new ArrayList<>(List.of(60)), 0),
    WOOD_CUTTER(100, MapAssetType.WOOD_CUTTER,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            3, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.STONE)),
            new ArrayList<>(List.of(80)), 1, new ArrayList<>(List.of(0)), 0),
    QUARRY(100, MapAssetType.QUARRY,0, Material.WOOD, 3,
            new ArrayList<>(List.of(CellType.STONE, CellType.SLATE, CellType.GRAVEL)),
            20, 150,
            null,
            new ArrayList<>(List.of(Material.STONE)),
            new ArrayList<>(List.of(50)), 1, new ArrayList<>(List.of(0)), 0),
    PITCH_RIG(100, MapAssetType.PITCH_RIG,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.PlAIN)),
            20, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.PITCH)),
            new ArrayList<>(List.of(50)), 1, new ArrayList<>(List.of(0)), 0),
    IRON_MINE(100, MapAssetType.IRON_MINE,0, Material.WOOD, 2,
            new ArrayList<>(List.of(CellType.IRON)),
            20, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.IRON)),
            new ArrayList<>(List.of(30)), 1, new ArrayList<>(List.of(0)), 0),
    MILL(100, MapAssetType.MILL,0, Material.WOOD, 3,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.WHEAT)),
            new ArrayList<>(List.of(Material.FLOUR)),
            new ArrayList<>(List.of(20)), 1, new ArrayList<>(List.of(0)), 0),
    INN(100, MapAssetType.INN,100, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.WINE)),
            new ArrayList<>(List.of(5)), 1, new ArrayList<>(List.of(0)), 0),
    HAUNTING_GROUND(100, MapAssetType.HAUNTING_GROUND,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.MEADOW, CellType.DENSE_MEADOW)),
            5, Integer.MAX_VALUE,
            null,
            new ArrayList<>(List.of(Material.MEAT)),
            new ArrayList<>(List.of(5)), 1, new ArrayList<>(List.of(0)), 0),
    ;

    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;
    public final int productionCapacity;
    public final ArrayList<Material> usingMaterial;
    public final ArrayList<Material> producingMaterial;
    public final ArrayList<Integer> rateOfProduction;
    public final int rateOfProcess;
    public final ArrayList<Integer> rateOfUsage;
    public final int populationCapacity;

    ProductionConstants(double maxHitPoint, MapAssetType type, int cost, Material neededMaterial,
                        int workerCount, ArrayList<CellType> buildingGroundType,
                        int neededMaterialAmount, int productionCapacity,
                        ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial,
                        ArrayList<Integer> rateOfProduction, int rateOfProcess, ArrayList<Integer> rateOfUsage, int populationCapacity) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
        this.productionCapacity = productionCapacity;
        this.usingMaterial = usingMaterial;
        this.producingMaterial = producingMaterial;
        this.rateOfProduction = rateOfProduction;
        this.rateOfProcess = rateOfProcess;
        this.rateOfUsage = rateOfUsage;
        this.populationCapacity = populationCapacity;
    }
}
