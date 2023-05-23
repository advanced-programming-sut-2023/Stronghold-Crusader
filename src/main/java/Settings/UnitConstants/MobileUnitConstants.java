package Settings.UnitConstants;

import model.enums.AssetType.MapAssetType;

public enum MobileUnitConstants {
    WORKER(1, 1, 0, 1, MapAssetType.WORKER, 0, true),
    ENGINEER(1, 3, 0, 100, MapAssetType.ENGINEER, 15, true),
    LADDER_MAN(1, 5, 0, 100, MapAssetType.LADDER_MAN, 10, false),
    SIEGE_TOWER(1, 1, 4, 200, MapAssetType.SIEGE_TOWER, 45, false),
    MOBILE_SHIELD(1, 1, 1, 60, MapAssetType.MOBILE_SHIELD, 5, false),
    COW(1, 2, 0, 1, MapAssetType.COW, 0, false);

    public final double defenceMultiplier;
    public final int moveSpeed;
    public final int engineersCount;
    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final boolean canClimbLadder;

    MobileUnitConstants(double defenceMultiplier, int moveSpeed, int engineersCount,
                        double maxHitPoint, MapAssetType type, int cost, boolean canClimbLadder) {
        this.defenceMultiplier = defenceMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineersCount = engineersCount;
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.canClimbLadder = canClimbLadder;
    }

}
