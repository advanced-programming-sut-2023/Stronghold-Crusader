package model.MapAsset.Building;

import model.User.Player;
import utils.Vector2D;

public class SymbolicBuilding extends Building {
    private final int capacityChangeRate;
    private final String changeType;

    public SymbolicBuilding(SymbolicBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.capacityChangeRate = reference.capacityChangeRate;
        this.changeType = reference.changeType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", capacityChangeRate=" + capacityChangeRate +
                ", changeType=" + changeType;
    }
}
