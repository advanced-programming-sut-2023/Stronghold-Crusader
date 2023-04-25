package model.MapAsset;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public abstract class MapAsset {
    protected final Vector2D coordinate;
    protected Player owner;
    protected double hitPoint;
    protected double maxHitPoint;
    protected MapAssetType type;

    public MapAsset(Vector2D coordinate, Player owner, MapAssetType type) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.type = type;
    }

    public abstract void getDamageFrom(MapAsset attacker);

    public void setMaxHitPoint(double maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
        this.hitPoint = maxHitPoint;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public MapAssetType getType() {
        return type;
    }
}
