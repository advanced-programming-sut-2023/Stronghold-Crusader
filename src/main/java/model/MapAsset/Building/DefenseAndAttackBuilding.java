package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class DefenseAndAttackBuilding extends Building{
    private int fireRange;
    private int defendRange;
    private int troopCapacity;
    private boolean siegeEquipmentAllowance;
    private boolean causeDamage;

    public DefenseAndAttackBuilding(Vector2D coordinate, Player owner, MapAsset type) {
        super(coordinate, owner, type);
    }
}
