package Settings.UnitConstants;

import model.enums.MapAssetType;

public enum AttackingUnitConstants {
    DOG(1, 8, 0, 70, 80, 1, false, false, MapAssetType.DOG),
    //European Units :
    ARCHER(1, 8, 0, 500, 20, 3, true, true, MapAssetType.ARCHER),
    CROSSBOWMAN(0.8, 3, 0, 800, 50, 2, false, false, MapAssetType.CROSSBOWMAN),
    SPEARMAN(1, 5, 0, 600, 100, 1, true, true, MapAssetType.SPEARMAN),
    PIKEMAN(0.6, 3, 0, 1700, 250, 1, false, true, MapAssetType.PIKEMAN),
    MACEMAN(0.8, 6, 0, 1500, 175, 1, true, true, MapAssetType.MACEMAN),
    SWORDSMAN(0.5, 1, 0, 2000, 350, 1, false, false, MapAssetType.SWORDSMAN),
    KNIGHT(0.5, 10, 0, 2000, 300, 1, false, false, MapAssetType.KNIGHT),
    //TODO fill this
    TUNNELER(0, 0, 0, 0, 0, 0, false, false, MapAssetType.TUNNELER),
    BLACK_MONK(1, 3, 0, 500, 50, 1, false, false, MapAssetType.BLACK_MONK),
    //Arabian Units:
    ARCHER_BOW(1, 8, 0, 500, 20, 3, false, true, MapAssetType.ARCHER_BOW),
    SLAVE(1, 8, 0, 100, 20, 1, false, true, MapAssetType.SLAVE),

    SLINGER(1, 8, 0, 150, 20, 2, false, false, MapAssetType.SLINGER),
    ASSASSIN(1, 5, 0, 1000, 100, 1, false, false, MapAssetType.ASSASSIN),
    HORSE_ARCHER(1, 10, 0, 1000, 50, 3, false, false, MapAssetType.HORSE_ARCHER),
    ARABIAN_SWORDSMAN(0.6, 5, 0, 1500, 275, 1, false, false, MapAssetType.ARABIAN_SWORDSMAN),
    FIRE_THROWER(1, 8, 0, 800, 100, 2, false, false, MapAssetType.FIRE_THROWER),
    //Siege warfare:
    BATTERING_RAM(1,3,4,400,500,1,false,false, MapAssetType.BATTERING_RAM),
    CATAPULT(1,3,2,400,400,3,false,false, MapAssetType.CATAPULT),
    STABLE_CATAPULT(1,0,3,500,500,4,false,false, MapAssetType.STABLE_CATAPULT),
    FIRE_CATAPULT(1,3,2,400,300,2,false,false, MapAssetType.FIRE_CATAPULT);
    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;
    private final int attackDamage;
    private final int attackRange;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    private final MapAssetType type;

    AttackingUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount, double maxHitPoint, int attackDamage
            , int attackRange, boolean canClimbLadder, boolean canModifyMoat, MapAssetType type) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.canClimbLadder = canClimbLadder;
        this.canModifyMoat = canModifyMoat;
        this.type = type;
    }

    public double getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getEngineerCount() {
        return engineerCount;
    }

    public double getMaxHitPoint() {
        return maxHitPoint;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isCanClimbLadder() {
        return canClimbLadder;
    }

    public boolean isCanModifyMoat() {
        return canModifyMoat;
    }

    public MapAssetType getType() {
        return type;
    }
}
