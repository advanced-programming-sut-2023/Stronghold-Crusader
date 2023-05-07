package model.enums.AssetType;

public enum People {
    KNIGHT,
    ASSASSIN,
    HORSEARCHER,
    SWORDSMAN,
    SLINGER,
    ARCHER,
    SPEARMAN,
    BLACKMONK,
    ARCHERBOW,
    PIKEMAN,
    ARABIAN_SWORDSMAN,
    SLAVE,
    BATTLERAM,
    CROSSBOWMAN,
    ENGINEER,
    LADDERMAN;

    public static boolean isContains(String input) {

        for (People p : People.values()) {
            if (p.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
