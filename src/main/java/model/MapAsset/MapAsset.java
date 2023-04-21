package model.MapAsset;

import model.Player;
import utils.Vector2D;

public abstract class MapAsset {
    protected final Vector2D coordinate;
    protected final Player owner;
    protected double hitPoint;
    protected double maxHitPoint;
    protected MapAsset type;

    public MapAsset(Vector2D coordinate, Player owner, MapAsset type) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.type = type;
    }

    public abstract void getDamageFrom(MapAsset attacker);

    public void setMaxHitPoint(double maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
        this.hitPoint = maxHitPoint;
    }

    public void setType(MapAsset type) {
        this.type = type;
    }
}
