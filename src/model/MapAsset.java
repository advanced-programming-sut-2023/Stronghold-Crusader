package model;

public abstract class MapAsset {
    private final Vector2D coordinate;
    private final Player owner;
    private double hitPoint;
    private double maxHitPoint;
    private String type;

    public MapAsset(Vector2D coordinate, Player owner) {
        this.coordinate = coordinate;
        this.owner = owner;
    }

    public abstract void getDamageFrom(MapAsset attacker);

    public void setMaxHitPoint(double maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
        this.hitPoint = maxHitPoint;
    }

    public void setType(String type) {
        this.type = type;
    }
}
