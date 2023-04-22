package Settings.UnitConstants;

public enum AttackingUnitConstants {
    DOG(1, 8, 0, 70, 80, 1, false, false),
    //European Units :
    ARCHER(1, 8, 0, 500, 20, 3, true, true),
    CROSSBOWMAN(0.8, 3, 0, 800, 50, 2, false, false),
    SPEARMAN(1, 5, 0, 600, 100, 1, true, true),
    PIKEMAN(0.6, 3, 0, 1700, 250, 1, false, true),
    MACEMAN(0.8, 6, 0, 1500, 175, 1, true, true),
    KNIGHT(0.5, 10, 0, 2000, 300, 1, false, false),
    SWORDSMAN(0.5, 1, 0, 2000, 350, 1, false, false),
    BLACK_MONK(1, 3, 0, 500, 50, 1, false, false),
    //Arabian Units:
    ARCHER_BOW(1, 8, 0, 500, 20, 3, false, true),
    SLAVE(1, 8, 0, 100, 20, 1, false, true),

    SLINGER(1, 8, 0, 150, 20, 2, false, false),
    ASSASSIN(1, 5, 0, 1000, 100, 1, false, false),
    HORSE_ARCHER(1, 10, 0, 1000, 50, 3, false, false),
    ARABIAN_SWORDSMAN(0.6, 5, 0, 1500, 275, 1, false, false),
    FIRE_THROWER(1, 8, 0, 800, 100, 2, false, false),
    //Siege warfare:
    BATTERING_RAM(1,3,4,400,500,1,false,false),
    CATAPULT(1,3,2,400,400,3,false,false),
    STABLE_CATAPULT(1,0,3,500,500,4,false,false),
    FIRE_CATAPULT(1,3,2,400,300,2,false,false);
    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;
    private final int attackDamage;
    private final int attackRange;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;

    AttackingUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount, double maxHitPoint, int attackDamage
            , int attackRange, boolean canClimbLadder, boolean canModifyMoat) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.canClimbLadder = canClimbLadder;
        this.canModifyMoat = canModifyMoat;
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


    }
