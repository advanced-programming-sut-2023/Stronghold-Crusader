package model.enums;

import java.util.ArrayList;
import java.util.List;

import static model.enums.MapAssetType.*;

public enum AttackTarget {
    //TODO fill these
    BUILDING(new ArrayList<>(List.of(ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK, ARCHER_BOW, SLAVE, SLINGER, ASSASSIN,
            HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN, DOG, COW))),
    HOUSE(new ArrayList<>(List.of(HEADQUARTER,BEAR_FACTORY, BAKERY, WHEAT_FIELD,
            GRAIN_FIELD, DIARY_FACTORY, APPLE_GARDEN,POLTURNER, FLETCHER, BLACKSMITH, ARMOURER, WOOD_CUTTER, QUARRY))),
    TROOPS(new ArrayList<>(List.of(ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK, ARCHER_BOW, SLAVE, SLINGER, ASSASSIN,
            HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN, DOG, COW)));
    private final ArrayList<MapAssetType> targets;

    AttackTarget(ArrayList<MapAssetType> targets) {
        this.targets = targets;
    }
}
