package model.MapAsset.MobileUnit;

import model.User.Player;
import model.enums.AssetType.Material;
import model.enums.AttackTarget;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    private final int attackDamage;
    private final int attackRange;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    public final ArrayList<Material> weapon;
    private final HashMap<AttackTarget, Boolean> targets;

    public AttackingUnit(AttackingUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.attackDamage = reference.attackDamage;
        this.attackRange = reference.attackRange;
        this.canClimbLadder = reference.canClimbLadder;
        this.canModifyMoat = reference.canModifyMoat;
        this.targets = reference.targets;
        this.weapon = reference.weapon;
    }

    public ArrayList<Material> getWeapons() {
        return weapon;
    }

    public void attack() {
    }

    public void stopAttack() {
    }

    @Override
    public String toString() {
        return super.toString() +
                ", attack damage=" + attackDamage +
                ", attack range=" + attackRange +
                ", climbs ladder=" + canClimbLadder +
                ", modifies moat=" + canModifyMoat +
                ", weapon=" + weapon +
                ", targets=" + targets;
    }
}
