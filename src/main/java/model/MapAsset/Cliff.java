package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.Direction;
import utils.Vector2D;

public class Cliff extends MapAsset {
    private final Direction direction;

    public Cliff(double maxHitPoint, MapAssetType type, int cost) {
        super(maxHitPoint, type, cost);
        direction = Direction.NORTH;
    }

    public Cliff(Cliff reference, Vector2D coordinate, Player owner, Direction direction) {
        super(reference, coordinate, owner);
        this.direction = direction;
    }

    @Override
    public void takeDamageFrom(AttackingUnit attacker) {
    }

    @Override
    public String toString() {
        return super.toString() +
                ", direction=" + direction.getName();
    }
}
