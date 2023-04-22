package Settings.BuildingConstants;

public enum DefenseAndAttackConstants {
    LOOKOUT_TOWER(1, 1, 2, 3, false),
    PERIMETER_TOWER(1, 1, 2, 3, false),
    DEFENSE_TOWER(1, 1, 2, 3, false),
    SQUARE_TOWER(1, 1, 2, 3, false),
    CIRCULAR_TOWER(1, 1, 2, 3, false);
    private int fireRange;
    private int hitpoint;
    private int defendRange;
    private int troopCapacity;
    private boolean siegeEquipmentAllowance;


    private DefenseAndAttackConstants(int fireRange, int defendRange, int troopCapacity, int hitpoint, boolean siegeEquipmentAllowance) {
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.troopCapacity = troopCapacity;
        this.hitpoint = hitpoint;
        this.siegeEquipmentAllowance = siegeEquipmentAllowance;
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

}
