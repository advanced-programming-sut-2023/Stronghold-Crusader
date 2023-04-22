package Settings.BuildingConstants;

import model.enums.Material;

import java.util.ArrayList;
import java.util.List;

public enum TrainingAndEmploymentConstants {
    BEAR_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    BAKERY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    WHEAT_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    GRAIN_FIELD(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    DIARY_FACTORY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    APPLE_GARDEN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    POLTURNER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    FLETCHER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    BLACKSMITH(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    ARMOURER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    WOOD_CUTTER(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    QUARRY(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    PITCH_RIG(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    IRON_MINE(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    MILL(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    INN(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1),
    HAUNTING_GROUND(1, new ArrayList<>(List.of(Material.APPLE)), new ArrayList<>(List.of(Material.APPLE)),
            new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(1, 2)), 1, 1);
    private int hitpoint;
    private ArrayList<Material> usingMaterial;
    private ArrayList<Material> producingMaterial;
    private ArrayList<Integer> usingRate;
    private ArrayList<Integer> producingRate;
    private int processRate;
    private int productionMaxCapacity;


    private TrainingAndEmploymentConstants(int hitpoint, ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial, ArrayList<Integer> usingRate,
                                           ArrayList<Integer> producingRate, int processRate, int productionMaxCapacity) {
        this.processRate = processRate;
        this.hitpoint = hitpoint;
        this.usingRate = usingRate;
        this.producingRate = producingRate;
        this.productionMaxCapacity = productionMaxCapacity;
        this.usingMaterial = usingMaterial;
        this.producingMaterial = producingMaterial;
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
}
