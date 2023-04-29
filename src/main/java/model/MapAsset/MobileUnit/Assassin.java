package model.MapAsset.MobileUnit;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Assassin extends AttackingUnit {
    private boolean invisibility;

    public Assassin(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }

    public boolean isInvisible() {
        return invisibility;
    }
}
