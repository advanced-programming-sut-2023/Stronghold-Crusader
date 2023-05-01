package Settings.UnitConstants;

import model.enums.MapAssetType;

public enum MobileUnitConstants {
    ENGINEER(1, 5, 0, 100, MapAssetType.ENGINEER, 15),
    LADDER_MAN(1, 8, 0, 100, MapAssetType.LADDER_MAN, 10),
    SIEGE_TOWER(1, 3, 4, 200, MapAssetType.SIEGE_TOWER, 45),
    MOBILE_SHIELD(1, 6, 1, 60, MapAssetType.MOBILE_SHIELD, 5),
    COW(1, 5, 0, 1, MapAssetType.COW, 0);

    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;
    private final MapAssetType type;
    private final int cost;

    MobileUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount,
                        double maxHitPoint, MapAssetType type, int cost) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public MapAssetType getType() {
        return type;
    }
}
