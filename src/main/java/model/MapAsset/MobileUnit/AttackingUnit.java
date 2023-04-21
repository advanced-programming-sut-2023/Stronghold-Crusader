package model.MapAsset.MobileUnit;

import model.*;
import model.MapAsset.MapAsset;
import model.enums.Armour;
import model.enums.ArmourPenetration;
import model.enums.AttackTarget;
import utils.Vector2D;

import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    private int attackDamage;
    private int attackRange;
    private boolean canClimbLadder;
    private boolean canModifyMoat;
    private HashMap<AttackTarget, Boolean> targets;
    private Armour armour;
    private ArmourPenetration armourPenetration;

    public AttackingUnit(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {
        super.getDamageFrom(attacker);
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setCanClimbLadder(boolean canClimbLadder) {
        this.canClimbLadder = canClimbLadder;
    }

    public void setCanModifyMoat(boolean canModifyMoat) {
        this.canModifyMoat = canModifyMoat;
    }

    public void setTargets(HashMap<AttackTarget, Boolean> targets) {
        this.targets = targets;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public void setArmourPenetration(ArmourPenetration armourPenetration) {
        this.armourPenetration = armourPenetration;
    }

    public void attack() {
    }

    public void stopAttack() {
    }
}
