package model.MapAsset;

import model.Player;
import model.enums.MapAssetType;
import model.enums.TreeType;
import utils.Vector2D;

public class Tree extends MapAsset {
    private final TreeType treeType;
    public Tree(Vector2D coordinate, Player owner, TreeType treeType) {
        super(coordinate, owner, MapAssetType.TREE);
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
