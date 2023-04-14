package model;

public abstract class MapAsset {
    private Vector2D coordinate;
    private Player owner;
    private double hitPoint;
    private double maxHitPoint;
    private String type;

    public MapAsset(Vector2D coordinate, Player owner, int maxHitPoint, String type) {
        this.coordinate = coordinate;
        this.owner = owner;
        this.hitPoint = maxHitPoint;
        this.maxHitPoint = maxHitPoint;
        this.type = type;
    }

}
