package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.CliffDirection;
import utils.Vector2D;

public class Cliff extends MapAsset {
    CliffDirection direction;

    public Cliff(Cliff reference, Vector2D coordinate, Player owner, CliffDirection direction) {
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
