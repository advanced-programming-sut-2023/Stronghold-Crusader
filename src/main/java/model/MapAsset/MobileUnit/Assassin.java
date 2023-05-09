package model.MapAsset.MobileUnit;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.AttackTarget;
import utils.Vector2D;

import java.util.ArrayList;

public class Assassin extends AttackingUnit {
    private boolean invisibility;

    public Assassin(Assassin reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }

    public Assassin(double maxHitPoint, MapAssetType type, int cost, int moveSpeed, double defenceMultiplier, int engineersCount, boolean canClimbLadder, ArrayList<Material> weapon, int attackDamage, int attackRange, boolean isAreaSplash, ArrayList<AttackTarget> targets) {
        super(maxHitPoint, type, cost, moveSpeed, defenceMultiplier, engineersCount, canClimbLadder, weapon, attackDamage, attackRange, isAreaSplash, targets);
    }

    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }

    public boolean isInvisible() {
        return invisibility;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", invisibility=" + invisibility;
    }
}
