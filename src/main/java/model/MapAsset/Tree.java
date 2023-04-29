package model.MapAsset;

import model.Player;
import model.enums.MapAssetType;
import model.enums.TreeType;
import utils.Vector2D;

public class Tree extends MapAsset {
    private final TreeType type;
    public Tree(Vector2D coordinate, Player owner, TreeType type) {
        super(coordinate, owner, MapAssetType.TREE);
        this.type = type;
    }

    public Tree(Tree reference, Vector2D coordinate, Player owner, TreeType type){
        super(reference, coordinate, owner);
        this.type = type;
    }

    public TreeType getTreeType() {
        return type;
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
