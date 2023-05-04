package model.Game;

import model.MapAsset.Building.Building;
import model.enums.AssetType.Material;
import model.enums.AssetType.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private int weaponCapacity;
    private int foodStuckCapacity;
    private int materialCapacity;
    private HashMap<Weapon, Integer> weapons;
    private HashMap<Material, Integer> foodStuck;
    private HashMap<Material, Integer> materials;

    public HashMap<Weapon, Integer> getWeapons() {
        return weapons;
    }

    public HashMap<Material, Integer> getFoodStuck() {
        return foodStuck;
    }

    public HashMap<Material, Integer> getMaterials() {
        return materials;
    }

    public int getMaterialCapacity(Material material) {
        return materialCapacity;
    }

    public void addArmors(Weapon weapon) {
        if (weapons.get(weapon) == null)
            weapons.put(weapon, 0);
        else weapons.replace(weapon, weapons.get(weapon), weapons.get(weapon) + 1);

    }

    public void addFoodStuck(Material material) {
        if (foodStuck.get(material) == null)
            foodStuck.put(material, 0);
        else foodStuck.replace(material, foodStuck.get(material), foodStuck.get(material) + 1);
    }

    public void addMaterials(Material material) {
        if (materials.get(material) == null)
            materials.put(material, 0);
        else materials.replace(material, materials.get(material), materials.get(material) + 1);
    }

    public void reduceMaterial(Material material, int number) {
        materials.replace(material, materials.get(material) - number);
    }

    public void reduceWeapon(ArrayList<Weapon> weapons, int number) {
        for (Weapon weapon : weapons) {
            this.weapons.replace(weapon, this.weapons.get(weapon) - number);
        }
    }

    public void setWeaponCapacity(int weaponCapacity) {
        this.weaponCapacity = weaponCapacity;
    }

    public void setFoodStuckCapacity(int foodStuckCapacity) {
        this.foodStuckCapacity = foodStuckCapacity;
    }

    public void setMaterialCapacity(int materialCapacity) {
        this.materialCapacity = materialCapacity;
    }

    public boolean hasEnoughMaterial(Building building){
        Material material = building.getNeededMaterial();
        int amount = building.getNumberOfMaterialNeeded();
        int inStock = materials.get(material);
        return amount <= inStock;
    }
}
