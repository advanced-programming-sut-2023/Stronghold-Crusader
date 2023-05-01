package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class DefenseAndAttackBuilding extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int troopCapacity;
    private int currentTroopCount;
    private final boolean siegeEquipmentAllowance;

    public DefenseAndAttackBuilding(DefenseAndAttackBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.fireRange = reference.fireRange;
        this.defendRange = reference.defendRange;
        this.troopCapacity = reference.troopCapacity;
        this.siegeEquipmentAllowance = reference.siegeEquipmentAllowance;
        currentTroopCount = 0;
    }

    public void changeTroopCount(int offset) {
        this.currentTroopCount += offset;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", fire range=" + fireRange +
                ", defend range=" + defendRange +
                ", capacity=" + currentTroopCount + '/' + troopCapacity +
                ", siege allowance=" + siegeEquipmentAllowance;
    }
}
