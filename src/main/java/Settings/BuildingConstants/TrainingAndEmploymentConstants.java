package Settings.BuildingConstants;

import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;


public enum TrainingAndEmploymentConstants {
    BARRACK(300, MapAssetType.BARRACK, 0, 0, Material.STONE,
            0, new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
            CellType.MEADOW, CellType.DENSE_MEADOW)),
            15, new ArrayList<>(List.of(MapAssetType.ARCHER, MapAssetType.CROSSBOWMAN,
            MapAssetType.SPEARMAN, MapAssetType.PIKEMAN, MapAssetType.MACEMAN, MapAssetType.SWORDSMAN,
            MapAssetType.KNIGHT, MapAssetType.LADDER_MAN)),
            0),
    MERCENARY_POST(300, MapAssetType.MERCENARY_POST, 0, 0, Material.WOOD,
            0, new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
            CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, new ArrayList<>(List.of(MapAssetType.ARCHER_BOW, MapAssetType.SLAVE,
            MapAssetType.SLINGER, MapAssetType.ASSASSIN, MapAssetType.HORSE_ARCHER, MapAssetType.ARABIAN_SWORDSMAN,
            MapAssetType.FIRE_THROWER)),
            0),
    ENGINEER_GUILD(300, MapAssetType.ENGINEER_GUILD, 100, 0, Material.WOOD,
            0, new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
            CellType.MEADOW, CellType.DENSE_MEADOW)),
            10, new ArrayList<>(List.of(MapAssetType.ENGINEER)),
            0),
    CHURCH(300, MapAssetType.CHURCH, 250, 0,null,
            0, new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
            CellType.MEADOW, CellType.DENSE_MEADOW)),
            0, new ArrayList<>(List.of(MapAssetType.BLACK_MONK)),
            2),
    CATHEDRAL(300, MapAssetType.CATHEDRAL, 1000, 0, null,
            0, new ArrayList<>(List.of(CellType.GRASS, CellType.FIELD,
            CellType.MEADOW, CellType.DENSE_MEADOW)),
            0, new ArrayList<>(List.of(MapAssetType.BLACK_MONK)),
            2)
    ;
    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;
    public final ArrayList<MapAssetType> peopleType;
    public final int popularityChangeRate;

    TrainingAndEmploymentConstants(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, ArrayList<MapAssetType> peopleType, int popularityChangeRate) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
        this.peopleType = peopleType;
        this.popularityChangeRate = popularityChangeRate;
    }
}