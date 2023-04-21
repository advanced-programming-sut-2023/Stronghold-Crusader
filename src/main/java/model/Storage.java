package model;

import model.enums.Material;
import utils.Pair;

import java.util.HashMap;

public class Storage {
    private HashMap<Material, Pair> Armours;
    private HashMap<Material, Pair> foodStuck;
    private HashMap<Material, Pair> materials;

    public HashMap<Material, Pair> getArmors() {
        return Armours;
    }

    public HashMap<Material, Pair> getFoodStuck() {
        return foodStuck;
    }

    public HashMap<Material, Pair> getMaterials() {
        return materials;
    }

    public void addArmors(Material material) {
    }

    public void addFoodStuck(Material material) {
    }

    public void addMaterials(Material material) {
    }
}
