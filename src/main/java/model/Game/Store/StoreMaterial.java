package model.Game.Store;

import java.util.ArrayList;

public class StoreMaterial {
    private static ArrayList<StoreMaterial> materialList;
    private String name;
    private int price;

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
        return "Material :" +
                "name= " + name + ' ' +
                ", price= " + price +
                "\n";
    }

    public static void setMaterialList(ArrayList<StoreMaterial> materialList) {
        StoreMaterial.materialList = materialList;
    }

    public static ArrayList<StoreMaterial> getMaterialList() {
        return materialList;
    }
}
