package model.MapAsset.MobileUnit;

import model.MapAsset.MapAsset;
import model.Player;
import model.enums.AttackTarget;
import model.enums.MapAssetType;
import utils.Vector2D;

import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    private final int attackDamage;
    private final int attackRange;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    private final HashMap<AttackTarget, Boolean> targets;

    public AttackingUnit(AttackingUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.attackDamage = reference.attackDamage;
        this.attackRange = reference.attackRange;
        this.canClimbLadder = reference.canClimbLadder;
        this.canModifyMoat = reference.canModifyMoat;
        this.targets = reference.targets;
    }

    public void attack() {
    }

    public void stopAttack() {
    }
}
