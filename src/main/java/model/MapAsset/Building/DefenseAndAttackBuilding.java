package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class DefenseAndAttackBuilding extends Building{
    private int fireRange;
    private int defendRange;
    private int troopCapacity;
    private boolean siegeEquipmentAllowance;

    public DefenseAndAttackBuilding(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    public void setDefendRange(int defendRange) {
        this.defendRange = defendRange;
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public void setSiegeEquipmentAllowance(boolean siegeEquipmentAllowance) {
        this.siegeEquipmentAllowance = siegeEquipmentAllowance;
    }

    public void setTroopCapacity(int troopCapacity) {
        this.troopCapacity = troopCapacity;
    }

    @Override
    public void setMaxHitPoint(double maxHitPoint) {
        super.setMaxHitPoint(maxHitPoint);
    }

}
