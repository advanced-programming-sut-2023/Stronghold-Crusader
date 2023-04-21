package model.MapAsset.MobileUnit;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import utils.Vector2D;

public class FireThrower extends AttackingUnit {
    public FireThrower(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    @Override
    public void attack() {
        super.attack();
    }

}
