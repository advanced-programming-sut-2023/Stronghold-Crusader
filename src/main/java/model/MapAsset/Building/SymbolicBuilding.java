package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class SymbolicBuilding extends Building{
    private int capacityChangeRate;
    private String changeType;

    public SymbolicBuilding(Vector2D coordinate, Player owner, MapAsset type) {
        super(coordinate, owner, type);
    }

    public void setCapacityChangeRate(int capacityChangeRate) {
        this.capacityChangeRate = capacityChangeRate;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
}
