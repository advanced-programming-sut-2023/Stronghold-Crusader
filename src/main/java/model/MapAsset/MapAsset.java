package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;

public abstract class MapAsset {
    protected final double maxHitPoint;
    protected final MapAssetType type;
    private final int cost;
    protected Vector2D coordinate;
    protected Player owner;
    protected double hitPoint;

    public MapAsset(MapAsset reference, Vector2D coordinate, Player owner) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.type = reference.type;
        this.maxHitPoint = reference.maxHitPoint;
        this.hitPoint = this.maxHitPoint;
        this.cost = reference.cost;
    }

    public MapAsset(double maxHitPoint, MapAssetType type, int cost) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
    }

    public void takeDamageFrom(AttackingUnit attacker) {
        hitPoint -= attacker.getAttackDamage();
    }

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

    public int getCost() {
        return cost;
    }

    public Vector2D getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase() +
                ", owner=" + owner.getNickname() +
                ": coordinate=" + coordinate.toString() +
                ", hp=" + hitPoint + '/' + maxHitPoint;
    }
}
