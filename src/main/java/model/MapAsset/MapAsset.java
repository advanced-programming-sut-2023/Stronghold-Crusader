package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public abstract class MapAsset {
    protected final Vector2D coordinate;
    protected final double maxHitPoint;
    protected final MapAssetType type;
    protected Player owner;
    protected double hitPoint;

    public MapAsset(MapAsset reference, Vector2D coordinate, Player owner) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.type = reference.type;
        this.maxHitPoint = reference.maxHitPoint;
        this.hitPoint = this.maxHitPoint;
    }

    public abstract void getDamageFrom(AttackingUnit attacker);

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public MapAssetType getType() {
        return type;
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public double getMaxHitPoint() {
        return maxHitPoint;
    }
}
