package model.enums;

public enum People {
    KNIGHT("European"),
    ASSASSIN("Arabian"),
    HORSEARCHER("Arabian"),
    SWORDSMAN("European"),
    SLINGER("Arabian"),
    ARCHER("European"),
    SPEARMAN("European"),
    BLACKMONK("ChurchMan"),
    ARCHERBOW("Arabian"),
    PIKEMAN("European"),
    ARABIAN_SWORDSMAN("Arabian"),
    SLAVE("Arabian"),
    BATTLERAM("Siege"),
    CROSSBOWMAN("European"),
    ENGINEER("Engineer"),
    LADDERMAN("Engineer");
    private final String type;
    People(String type ){
        this.type = type;
    }
    public boolean checkType(String type){return this.type.equals(type);}
    public static boolean isContains(String input) {

        for (People p : People.values()) {
            if (p.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
