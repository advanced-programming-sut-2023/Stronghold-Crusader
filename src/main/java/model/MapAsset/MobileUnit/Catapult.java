package model.MapAsset.MobileUnit;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Catapult extends AttackingUnit {

    public Catapult(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    @Override
    public void attack() {
        super.attack();
    }
}
