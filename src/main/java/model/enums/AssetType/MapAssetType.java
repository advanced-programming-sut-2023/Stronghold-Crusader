package model.enums.AssetType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public enum MapAssetType {
    //Buildings
    HEADQUARTER, LOOKOUT_TOWER, PERIMETER_TOWER, DEFENSE_TOWER, SQUARE_TOWER, CIRCULAR_TOWER, BEAR_FACTORY, BAKERY, WHEAT_FIELD,
    GRAIN_FIELD, DIARY_FACTORY, APPLE_GARDEN, POLTURNER, FLETCHER, BLACKSMITH, ARMOURER, WOOD_CUTTER, QUARRY, SHORT_WALL, WALL, STAIRS,
    PITCH_RIG, IRON_MINE, MILL, INN, KILLING_PIT, SIEGE_TENT, STABLE, BARRACK, MERCENARY_POST, ENGINEER_GUILD, CAGED_WARDOG,
    CHURCH, CATHEDRAL, TUNNELER_POST, HOUSE, SMALL_GATEHOUSE, BIG_GATEHOUSE, ARMOURY, FOOD_STORAGE, STORE_HOUSE, STORE, OX_TETHER, DRAW_BRIDGE, HAUNTING_GROUND,

    //People
    WORKER, ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK, ARCHER_BOW, SLAVE, SLINGER, ASSASSIN,
    HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN,

    //Siege warfare
    BATTERING_RAM, CATAPULT, STABLE_CATAPULT, FIRE_CATAPULT, SIEGE_TOWER, MOBILE_SHIELD,
    DOG, COW,

    //Nature objects
    TREE, CLIFF;

    public static MapAssetType getMapAssetType(String typeName) {
        for (MapAssetType type : MapAssetType.values())
            if (type.name().equalsIgnoreCase(typeName)) return type;
        return null;
    }

    public static MapAssetType getPerson(String input) {
        for (MapAssetType person : getPeople()) {
            if (person.name().equalsIgnoreCase(input))
                return person;
        }
        return null;
    }

    public static ArrayList<MapAssetType> getPeople() {
        return new ArrayList<>(List.of(ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK,
                ARCHER_BOW, SLAVE, SLINGER, ASSASSIN, HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN));
    }
}
