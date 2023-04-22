package model.MapAsset;

import model.Player;
import model.enums.AssestType;
import utils.Vector2D;

public abstract class MapAsset {
    protected final Vector2D coordinate;
    protected final Player owner;
    protected double hitPoint;
    protected double maxHitPoint;
    protected AssestType type;

    public MapAsset(Vector2D coordinate, Player owner, AssestType type) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.type = type;
    }

    public abstract void getDamageFrom(MapAsset attacker);

    public void setMaxHitPoint(double maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
        this.hitPoint = maxHitPoint;
    }

}
