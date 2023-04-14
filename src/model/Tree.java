package model;

public class Tree extends MapAsset {
    public Tree(Vector2D coordinate, Player owner, TreeType type) {
        super(coordinate, owner);
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
