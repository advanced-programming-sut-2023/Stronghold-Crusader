package model.enums.AssetType;

public enum BuildingType {
    HEADQUARTER(BuildingCategory.NORMAL),
    LOOKOUT_TOWER(BuildingCategory.DEFENSE_AND_ATTACK),
    PERIMETER_TOWER(BuildingCategory.DEFENSE_AND_ATTACK),
    DEFENSE_TOWER(BuildingCategory.DEFENSE_AND_ATTACK),
    SQUARE_TOWER(BuildingCategory.DEFENSE_AND_ATTACK),
    CIRCULAR_TOWER(BuildingCategory.DEFENSE_AND_ATTACK),
    BEAR_FACTORY(BuildingCategory.PRODUCTION),
    BAKERY(BuildingCategory.PRODUCTION),
    WHEAT_FIELD(BuildingCategory.PRODUCTION),
    GRAIN_FIELD(BuildingCategory.PRODUCTION),
    DIARY_FACTORY(BuildingCategory.PRODUCTION),
    APPLE_GARDEN(BuildingCategory.PRODUCTION),
    POLTURNER(BuildingCategory.PRODUCTION),
    FLETCHER(BuildingCategory.PRODUCTION),
    BLACKSMITH(BuildingCategory.PRODUCTION),
    ARMOURER(BuildingCategory.PRODUCTION),
    WOOD_CUTTER(BuildingCategory.PRODUCTION),
    QUARRY(BuildingCategory.PRODUCTION),
    PITCH_RIG(BuildingCategory.PRODUCTION),
    IRON_MINE(BuildingCategory.PRODUCTION),
    MILL(BuildingCategory.PRODUCTION),
    INN(BuildingCategory.PRODUCTION),
    KILLING_PIT(BuildingCategory.NORMAL),
    SIEGE_TENT(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    BARRACK(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    MERCENARY_POST(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    ENGINEER_GUILD(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    CHURCH(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    CATHEDRAL(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    TUNNELER_POST(BuildingCategory.TRAINING_AND_EMPLOYMENT),
    HOUSE(BuildingCategory.NORMAL),
    SMALL_GATEHOUSE(BuildingCategory.NORMAL),
    BIG_GATEHOUSE(BuildingCategory.NORMAL),
    STORE(BuildingCategory.NORMAL),
    ARMOURY(BuildingCategory.STORAGE),
    FOOD_STORAGE(BuildingCategory.STORAGE),
    STORE_HOUSE(BuildingCategory.STORAGE),
    STABLE(BuildingCategory.STORAGE);

    private final BuildingCategory category;
    BuildingType(BuildingCategory category){
        this.category = category;
    }

    public static BuildingType getType(String typeName){
        for(BuildingType type : BuildingType.values()){
            if(type.name().equals(typeName)) return type;
        }
        return null;
    }
}
