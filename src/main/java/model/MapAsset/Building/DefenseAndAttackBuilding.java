package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class DefenseAndAttackBuilding extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int troopCapacity;
    private final boolean siegeEquipmentAllowance;

    public DefenseAndAttackBuilding(DefenseAndAttackBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.fireRange = reference.fireRange;
        this.defendRange = reference.defendRange;
        this.troopCapacity = reference.troopCapacity;
        this.siegeEquipmentAllowance = reference.siegeEquipmentAllowance;
    }

}
