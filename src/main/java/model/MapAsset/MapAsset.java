package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;

public class MapAsset {
    protected final double maxHitPoint;
    protected final MapAssetType type;
    private final int cost;
    protected Vector2D coordinate;
    protected Player owner;
    protected double hitPoint;

    public MapAsset(MapAsset reference, Vector2D coordinate, Player owner) {
        if (coordinate == null)
            this.coordinate = null;
        else
            this.coordinate = new Vector2D(coordinate.x, coordinate.y);
        this.owner = owner;
        this.type = reference.type;
        this.maxHitPoint = reference.maxHitPoint;
        this.hitPoint = reference.maxHitPoint;
        this.cost = reference.cost;
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
        return new Vector2D(coordinate.x, coordinate.y);
    }

    @Override
    public String toString() {
        String ownerStr = "null";
        if (owner != null)
            ownerStr = owner.getNickname();
        String coordStr = "";
        if (coordinate != null)
            coordStr = ": coordinate=" + coordinate;
        return type.name().toLowerCase() + " (" + ownerStr + ")" + coordStr + ", hp=" + hitPoint + '/' + maxHitPoint;
    }
}
