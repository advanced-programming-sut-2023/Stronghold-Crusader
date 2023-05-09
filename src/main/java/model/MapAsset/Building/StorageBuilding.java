package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorageBuilding extends Building {
    private final int totalCapacity;
    private final HashMap<Material, Integer> storage;

    public StorageBuilding(StorageBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.totalCapacity = reference.totalCapacity;
        this.storage = reference.storage;
    }

    public StorageBuilding(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int totalCapacity, List<Material> storageArrayList) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
        this.totalCapacity = totalCapacity;
        this.storage = new HashMap<>();
        for (Material material : storageArrayList)
            storage.put(material, 0);
    }

    public void changeStock(Material material, int offset) {
        int pastValue = storage.get(material);
        storage.put(material, offset + pastValue);
    }

    public int usedStorage() {
        int stockSum = 0;
        for (Integer stock : storage.values())
            stockSum += stock;
        return stockSum;
    }

    public boolean hasMaterial(Material material) {
        return storage.containsKey(material);
    }

    public int getRemainingCapacity() {
        return totalCapacity - this.usedStorage();
    }

    public int getStock(Material material) {
        return storage.get(material);
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
