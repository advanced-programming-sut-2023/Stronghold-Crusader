package model.MapAsset;

import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.TreeType;
import utils.Vector2D;

public class Tree extends MapAsset {
    private final TreeType treeType;

    public Tree(Tree reference, Vector2D coordinate, Player owner, TreeType treeType) {
        super(reference, coordinate, owner);
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }
}
