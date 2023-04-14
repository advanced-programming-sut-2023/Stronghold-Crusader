package model;

import java.util.HashMap;

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
