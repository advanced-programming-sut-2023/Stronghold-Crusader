package model.MapAsset.MobileUnit;

import model.User.Player;
import utils.Vector2D;

public class Assassin extends AttackingUnit {
    private boolean invisibility;
    public Assassin(Assassin reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }

    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }

    public boolean isInvisible() {
        return invisibility;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", invisibility=" + invisibility;
    }
}
