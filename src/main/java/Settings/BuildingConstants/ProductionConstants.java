package Settings.BuildingConstants;

import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

public enum ProductionConstants {
    // TODO fill this
//    BEAR_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BEAR_FACTORY),
//    BAKERY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BAKERY),
//    WHEAT_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.WHEAT_FIELD),
//    GRAIN_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.GRAIN_FIELD),
//    DIARY_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.DIARY_FACTORY),
//    APPLE_GARDEN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.APPLE_GARDEN),
//    POLTURNER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.POLTURNER),
//    FLETCHER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.FLETCHER),
//    BLACKSMITH(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BLACKSMITH),
//    ARMOURER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.ARMOURER),
//    WOOD_CUTTER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.WOOD_CUTTER),
//    QUARRY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.QUARRY),
//    PITCH_RIG(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.PITCH_RIG),
//    IRON_MINE(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.IRON_MINE),
//    MILL(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.MILL),
//    INN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.INN),
//    HAUNTING_GROUND(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
//            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.KILLING_PIT)
    ;

    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int populationCapacity;
    public final Material neededMaterial;
    public final int workerCount;
    public final ArrayList<CellType> buildingGroundType;
    public final int neededMaterialAmount;
    public final int productionCapacity;
    public final ArrayList<Material> usingMaterial;
    public final ArrayList<Material> producingMaterial;
    public final ArrayList<Integer> rateOfUsage;
    public final ArrayList<Integer> rateOfProduction;
    public final int rateOfProcess;

    ProductionConstants(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int productionCapacity, ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial, ArrayList<Integer> rateOfUsage, ArrayList<Integer> rateOfProduction, int rateOfProcess) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.populationCapacity = populationCapacity;
        this.neededMaterial = neededMaterial;
        this.workerCount = workerCount;
        this.buildingGroundType = buildingGroundType;
        this.neededMaterialAmount = neededMaterialAmount;
        this.productionCapacity = productionCapacity;
        this.usingMaterial = usingMaterial;
        this.producingMaterial = producingMaterial;
        this.rateOfUsage = rateOfUsage;
        this.rateOfProduction = rateOfProduction;
        this.rateOfProcess = rateOfProcess;
    }
}
