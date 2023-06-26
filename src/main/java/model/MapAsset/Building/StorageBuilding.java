package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import utils.Vector2D;

import java.util.HashMap;

public class StorageBuilding extends Building {
    private final int totalCapacity;
    private final HashMap<Material, Integer> storage;

    public StorageBuilding(StorageBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.totalCapacity = reference.totalCapacity;
        this.storage = reference.storage;
        if (reference.type.equals(MapAssetType.STORE_HOUSE)){
            storage.replace(Material.STONE, 500);
            storage.replace(Material.WOOD, 500);
        }
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

    @Override
    public String toString() {
        return super.toString() +
                ", totalCapacity=" + totalCapacity +
                ", storage=" + storage;
    }
}
