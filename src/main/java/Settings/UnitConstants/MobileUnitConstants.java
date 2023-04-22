package Settings.UnitConstants;

public enum MobileUnitConstants {
    ENGINEER(1, 5, 0, 100),
    LADDER_MAN(1,6,0, 100),
    SIEGE_TOWER(1, 3,2, 200),
    MOBILE_SHIELD(1, 8,1, 60),
    COW(1, 5, 0, 1);

    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;

    MobileUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount, double maxHitPoint) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
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
}
