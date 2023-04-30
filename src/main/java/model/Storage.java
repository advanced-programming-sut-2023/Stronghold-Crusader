package model;

import model.enums.Material;

import java.util.HashMap;

public class Storage {
    private int ArmourCapacity;
    private int foodStuckCapacity;
    private int materialCapacity;
    private HashMap<Material, Integer> Armours;
    private HashMap<Material, Integer> foodStuck;
    private HashMap<Material, Integer> materials;

    public HashMap<Material, Integer> getArmours() {
        return Armours;
    }

    public HashMap<Material, Integer> getFoodStuck() {
        return foodStuck;
    }

    public HashMap<Material, Integer> getMaterials() {
        return materials;
    }

    public int getArmourCapacity() {
        return ArmourCapacity;
    }

    public int getFoodStuckCapacity() {
        return foodStuckCapacity;
    }

    public int getMaterialCapacity() {
        return materialCapacity;
    }

    public void addArmors(Material material) {
        if (Armours.get(material) == null)
            Armours.put(material, 0);
        else Armours.replace(material, Armours.get(material), Armours.get(material) + 1);

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
    public void reduceMaterial(Material material,int number){
        materials.replace(material, materials.get(material) - number);
    }

    public void setArmourCapacity(int armourCapacity) {
        ArmourCapacity = armourCapacity;
    }

    public void setFoodStuckCapacity(int foodStuckCapacity) {
        this.foodStuckCapacity = foodStuckCapacity;
    }

    public void setMaterialCapacity(int materialCapacity) {
        this.materialCapacity = materialCapacity;
    }
}
