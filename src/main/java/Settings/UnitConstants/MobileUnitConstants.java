package Settings.UnitConstants;

import model.enums.MapAssetType;

public enum MobileUnitConstants {
    ENGINEER(1, 5, 0, 100, MapAssetType.ENGINEER),
    LADDER_MAN(1,8,0, 100, MapAssetType.LADDER_MAN),
    SIEGE_TOWER(1, 3,4, 200, MapAssetType.SIEGE_TOWER),
    MOBILE_SHIELD(1, 6,1, 60, MapAssetType.MOBILE_SHIELD),
    COW(1, 5, 0, 1, MapAssetType.COW);

    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;
    private final MapAssetType type;

    MobileUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount, double maxHitPoint, MapAssetType type) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
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

    public MapAssetType getType() {
        return type;
    }
}
