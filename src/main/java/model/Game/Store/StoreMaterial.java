package model.Game.Store;

import java.util.ArrayList;

public class StoreMaterial {
    private static ArrayList<StoreMaterial> materialList;
    private String name;
    private int price;
    private String type;

    public StoreMaterial(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static StoreMaterial getInstance(String materialName) {
        for (StoreMaterial m : materialList) {
            if (m.name.equals(materialName)) return m;
        }
        return null;
    }

    @Override
    public String toString() {
        return  name + "\n" + price;
    }

    public static void setMaterialList(ArrayList<StoreMaterial> materialList) {
        StoreMaterial.materialList = materialList;
    }

    public static ArrayList<StoreMaterial> getMaterialList() {
        return materialList;
    }
    public static ArrayList<StoreMaterial> getMaterials() {
        ArrayList<StoreMaterial> selected = new ArrayList<>();
        for (StoreMaterial storeMaterial : materialList){
            if (storeMaterial.type.equals("material")){
                selected.add(storeMaterial);
            }
        }
        return selected;
    }
    public static ArrayList<StoreMaterial> getFoods() {
        ArrayList<StoreMaterial> selected = new ArrayList<>();
        for (StoreMaterial storeMaterial : materialList){
            if (storeMaterial.type.equals("food")){
                selected.add(storeMaterial);
            }
        }
        return selected;
    }
    public static ArrayList<StoreMaterial> getWeapons() {
        ArrayList<StoreMaterial> selected = new ArrayList<>();
        for (StoreMaterial storeMaterial : materialList){
            if (storeMaterial.type.equals("weapon")){
                selected.add(storeMaterial);
            }
        }
        return selected;
    }
}
