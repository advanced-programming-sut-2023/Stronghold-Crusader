package model.MapAsset.MobileUnit;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import utils.Vector2D;

public class StableCatapult extends AttackingUnit {
    public StableCatapult(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    @Override
    public void attack() {
        super.attack();
    }
}
