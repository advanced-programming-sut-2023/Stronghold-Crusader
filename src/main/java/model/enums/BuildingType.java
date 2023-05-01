package model.enums;

public enum BuildingType {
    HEADQUARTER("Other"), LOOKOUT_TOWER("DandA"), PERIMETER_TOWER("DandA"),
    DEFENSE_TOWER("DandA"), SQUARE_TOWER("DandA"), CIRCULAR_TOWER("DandA"),
    BEAR_FACTORY("Production"), BAKERY("Production"), WHEAT_FIELD("Production"),
    GRAIN_FIELD("Production"), DIARY_FACTORY("Production"), APPLE_GARDEN("Production"),
    POLTURNER("Production"), FLETCHER("Production"), BLACKSMITH("Production"),
    ARMOURER("Production"), WOOD_CUTTER("Production"), QUARRY("Production"),
    PITCH_RIG("Production"), IRON_MINE("Production"), MILL("Production"),
    INN("Production"), HAUNTING_GROUND("Production"), SIEGE_TENT("Production"),
    STABLE("TAndA"), BARRACK("TAndA"), MERCENARY_POST("TAndA"), ENGINEER_GUILD("TAndA"),
    CHURCH("TAndA"), CATHEDRAL("TAndA"), TUNNELER_POST("TAndA"),
    HOUSE("Symbolic"), SMALL_GATEHOUSE("Symbolic"), BIG_GATEHOUSE("Symbolic"),
    ARMOURY("Symbolic"), FOOD_STORAGE("Symbolic"), STORE_HOUSE("Symbolic"),
    STORE("Other"), OX_TETHER("Other");
    private String category;
    BuildingType(String category){
        this.category = category;
    }
}
