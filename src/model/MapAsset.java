package model;

public abstract class MapAsset {
    private Vector2D coordinate;
    private Player owner;

    public MapAsset(Vector2D coordinate, Player owner) {
        this.coordinate = coordinate;
        this.owner = owner;
    }
}
