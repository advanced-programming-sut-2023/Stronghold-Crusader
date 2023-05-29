package model.enums.AssetType;

import java.util.ArrayList;
import java.util.List;

public enum Material {
    // foods
    MEAT, APPLE, BREAD, CHEESE,

    //Other
    GRAIN, BEAR, FLOUR, WHEAT, WOOD, STONE, SPEAL, PICK, ROCKBLOCK, PITCH, WINE, IRON, LEATHER_WEST,

    //Weapons
    ARMOUR, MACE, SWORD, BOW, CROSS_BOW, SPEAR, PIKE,

    //Stable
    HORSE;

    public static boolean contains(String input) {

        for (Material m : Material.values()) {
            if (m.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Material> getMainFoods() {
        return new ArrayList<>(List.of(MEAT, CHEESE, APPLE, BREAD));
    }

    public static Material getMaterial(String materialName) {
        for (Material m : Material.values()) {
            if (m.name().equalsIgnoreCase(materialName))
                return m;
        }
        return null;
    }
}
