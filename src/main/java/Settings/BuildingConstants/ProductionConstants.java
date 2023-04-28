package Settings.BuildingConstants;

import model.enums.MapAssetType;
import model.enums.Material;

import java.util.ArrayList;
import java.util.List;

public enum ProductionConstants {
    BEAR_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BEAR_FACTORY),
    BAKERY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BAKERY),
    WHEAT_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.WHEAT_FIELD),
    GRAIN_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.GRAIN_FIELD),
    DIARY_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.DIARY_FACTORY),
    APPLE_GARDEN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.APPLE_GARDEN),
    POLTURNER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.POLTURNER),
    FLETCHER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.FLETCHER),
    BLACKSMITH(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.BLACKSMITH),
    ARMOURER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.ARMOURER),
    WOOD_CUTTER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.WOOD_CUTTER),
    QUARRY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.QUARRY),
    PITCH_RIG(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.PITCH_RIG),
    IRON_MINE(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.IRON_MINE),
    MILL(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.MILL),
    INN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.INN),
    HAUNTING_GROUND(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1, MapAssetType.HAUNTING_GROUND);
    private final MapAssetType type;
    private final int hitpoint;
    private final ArrayList<Material> usingMaterial;
    private final ArrayList<Material> producingMaterial;
    private final ArrayList<Integer> usingRate;
    private final ArrayList<Integer> producingRate;
    private final int processRate;
    private final int productionMaxCapacity;


    private ProductionConstants(int hitpoint, ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial, ArrayList<Integer> usingRate,
                                ArrayList<Integer> producingRate, int processRate, int productionMaxCapacity, MapAssetType type) {
        this.processRate = processRate;
        this.hitpoint = hitpoint;
        this.usingRate = usingRate;
        this.producingRate = producingRate;
        this.productionMaxCapacity = productionMaxCapacity;
        this.usingMaterial = usingMaterial;
        this.producingMaterial = producingMaterial;
        this.type = type;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public ArrayList<Integer> getProducingRate() {
        return producingRate;
    }

    public ArrayList<Integer> getUsingRate() {
        return usingRate;
    }

    public ArrayList<Material> getProducingMaterial() {
        return producingMaterial;
    }

    public ArrayList<Material> getUsingMaterial() {
        return usingMaterial;
    }

    public int getProcessRate() {
        return processRate;
    }

    public int getProductionMaxCapacity() {
        return productionMaxCapacity;
    }

    public MapAssetType getType() {
        return type;
    }
}
