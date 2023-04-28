package Settings.BuildingConstants;

import model.enums.MapAssetType;

public enum DefenseAndAttackConstants {
    LOOKOUT_TOWER(1, 1, 2, 3, false, MapAssetType.LOOKOUT_TOWER), PERIMETER_TOWER(1, 1, 2, 3, false, MapAssetType.PERIMETER_TOWER), DEFENSE_TOWER(1, 1, 2, 3, false, MapAssetType.DEFENSE_TOWER), SQUARE_TOWER(1, 1, 2, 3, false, MapAssetType.SQUARE_TOWER), CIRCULAR_TOWER(1, 1, 2, 3, false, MapAssetType.CIRCULAR_TOWER);
    private final int fireRange;
    private final int hitpoint;
    private final int defendRange;
    private final int troopCapacity;
    private final boolean siegeEquipmentAllowance;

    private final MapAssetType type;


    private DefenseAndAttackConstants(int fireRange, int defendRange, int troopCapacity, int hitpoint, boolean siegeEquipmentAllowance, MapAssetType type) {
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.troopCapacity = troopCapacity;
        this.hitpoint = hitpoint;
        this.siegeEquipmentAllowance = siegeEquipmentAllowance;
        this.type = type;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public boolean isSiegeEquipmentAllowance() {
        return siegeEquipmentAllowance;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getTroopCapacity() {
        return troopCapacity;
    }

    public MapAssetType getType() {
        return type;
    }
}
