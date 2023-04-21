package model.MapAsset.MobileUnit;

import model.Player;
import utils.Vector2D;

public class Assassin extends AttackingUnit {
    private boolean invisibility;

    public Assassin(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }

    public boolean isInvisible() {
        return invisibility;
    }
}
