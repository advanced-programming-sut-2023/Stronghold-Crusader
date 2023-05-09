package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.TreeType;
import utils.Vector2D;

public class Tree extends MapAsset {
    private final TreeType treeType;

    public Tree(Tree reference, Vector2D coordinate, Player owner, TreeType treeType) {
        super(reference, coordinate, owner);
        this.treeType = treeType;
    }

    public Tree(double maxHitPoint, MapAssetType type, int cost) {
        super(maxHitPoint, type, cost);
        treeType = TreeType.CHERRY;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", type=" + treeType.name().toLowerCase();
    }
}
