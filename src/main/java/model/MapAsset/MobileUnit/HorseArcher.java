package model.MapAsset.MobileUnit;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class HorseArcher extends AttackingUnit {
    public HorseArcher(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    @Override
    public void attack() {
        super.attack();
    }

}
