package model.enums.AssetType;

public enum Material {
    GRAIN,
    BEAR,
    FLOUR,
    BREAD,
    WHEAT,
    CHEESE,
    APPLE,
    WOOD,
    STONE,
    SPEAL,
    PICK,
    BOW,
    IRON,
    ARMOUR,
    MACE,
    SWORD,
    ROCKBLOCK,
    PITCH,
    WINE,
    MEAT;

    public static boolean isContains(String input) {

        for (Material m : Material.values()) {
            if (m.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
