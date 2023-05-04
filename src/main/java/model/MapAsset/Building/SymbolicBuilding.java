package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.Material;
import utils.Vector2D;

import java.util.HashMap;

public class SymbolicBuilding extends Building {
    private final int populationAmount;
    private final int totalCapacity;
    private final HashMap<Material, Integer> storage;

    public SymbolicBuilding(SymbolicBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.totalCapacity = reference.totalCapacity;
        this.storage = reference.storage;
        this.populationAmount = reference.populationAmount;
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

    public int getPopulationAmount() {
        return populationAmount;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", totalCapacity=" + totalCapacity +
                ", storage=" + storage;
    }
}
