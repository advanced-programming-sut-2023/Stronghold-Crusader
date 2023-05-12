package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum ProductionConstants {
    BEAR_FACTORY(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1 ),
    BAKERY(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    WHEAT_FIELD(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    GRAIN_FIELD(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    DIARY_FACTORY(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    APPLE_GARDEN(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    POLTURNER(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    FLETCHER(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    BLACKSMITH(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    ARMOURER((100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    WOOD_CUTTER(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    QUARRY(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    PITCH_RIG(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    IRON_MINE(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    MILL(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    INN(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
    HAUNTING_GROUND(100, MapAssetType.BEAR_FACTORY,0, Material.WOOD, 1,
            new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
                    CellType.PlAIN, CellType.MEADOW, CellType.DENSE_MEADOW)),
            20, Integer.MAX_VALUE,
            new ArrayList<>(List.of(Material.GRAIN)),
            new ArrayList<>(List.of(Material.BEAR)),
            new ArrayList<>(List.of(5)), 1),
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

    ProductionConstants(double maxHitPoint, MapAssetType type, int cost, Material neededMaterial,
                        int workerCount, ArrayList<CellType> buildingGroundType,
                        int neededMaterialAmount, int productionCapacity,
                        ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial,
                        ArrayList<Integer> rateOfProduction, int rateOfProcess) {
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
    }
}
