package model.enums;

import model.enums.AssetType.MapAssetType;

import java.util.ArrayList;
import java.util.List;

import static model.enums.AssetType.MapAssetType.*;

public enum AttackTarget {
    BUILDING(new ArrayList<>(List.of(HEADQUARTER, LOOKOUT_TOWER, PERIMETER_TOWER, DEFENSE_TOWER, SQUARE_TOWER, CIRCULAR_TOWER, BEAR_FACTORY, BAKERY, WHEAT_FIELD,
            GRAIN_FIELD, DIARY_FACTORY, APPLE_GARDEN, POLTURNER, FLETCHER, BLACKSMITH, ARMOURER, WOOD_CUTTER, QUARRY, SHORT_WALL, WALL, STAIRS,
            PITCH_RIG, IRON_MINE, MILL, INN, KILLING_PIT, SIEGE_TENT, STABLE, BARRACK, MERCENARY_POST, ENGINEER_GUILD, CAGED_WARDOG,
            CHURCH, CATHEDRAL, HOUSE, SMALL_GATEHOUSE, BIG_GATEHOUSE, ARMOURY, FOOD_STORAGE, STORE_HOUSE, STORE, OX_TETHER, DRAW_BRIDGE, HAUNTING_GROUND, TREE))),
    TROOPS(new ArrayList<>(List.of(WORKER, ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK, ARCHER_BOW, SLAVE, SLINGER, ASSASSIN,
            HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN, BATTERING_RAM, CATAPULT, STABLE_CATAPULT, FIRE_CATAPULT, SIEGE_TOWER, MOBILE_SHIELD, DOG, COW)));
    private final ArrayList<MapAssetType> items;

    AttackTarget(ArrayList<MapAssetType> targets) {
        this.items = targets;
    }

    public boolean contains(MapAssetType type){
        for (MapAssetType item : items)
            if(item == type) return true;
        return false;
    }

    public ArrayList<MapAssetType> getItems() {
        return items;
    }
}
