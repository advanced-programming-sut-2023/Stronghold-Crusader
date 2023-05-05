package model.enums.AssetType;

import java.util.ArrayList;

public enum Material {
    //Main foods
    MEAT,
    APPLE,
    BREAD,
    CHEESE,

    //Other
    GRAIN,
    BEAR,
    FLOUR,
    WHEAT,
    WOOD,
    STONE,
    SPEAL,
    PICK,
    ROCKBLOCK,
    PITCH,
    WINE,
    IRON,

    //Weapons
    ARMOUR,
    MACE,
    SWORD,
    BOW,
    CROSS_BOW,
    SPEAR,
    PIKE,
    HORSE;

    public static boolean Contains(String input) {

        for (Material m : Material.values()) {
            if (m.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static Material[] getMainFoods(){
        Material[] foods = new Material[4];
        foods[0] = MEAT;
        foods[1] = CHEESE;
        foods[2] = APPLE;
        foods[3] = BREAD;
        return foods;
    }

    public static Material getMaterial(String materialName){
        for (Material m : Material.values()) {
            if (m.name().equals(materialName)) {
                return m;
            }
        }
        return null;
    }
}
