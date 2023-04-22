package model.MapAsset.MobileUnit;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Knight extends AttackingUnit {

    public Knight(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    @Override
    public void attack() {
        super.attack();
    }
}
