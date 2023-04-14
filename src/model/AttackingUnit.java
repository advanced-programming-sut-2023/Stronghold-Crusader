package model;

import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    private int attackDamage;
    private int attackRange;
    private boolean canClimbLadder;
    private boolean canModifyMoat;
    private HashMap<AttackTarget, Boolean> targets;
    private Armour armour;
    private ArmourPenetration armourPenetration;

    public AttackingUnit(Vector2D coordinate, Player owner, int maxHitPoint, String type, int moveSpeed, int defenceMultiplier, int engineersCount, int attackDamage, int attackRange, boolean canClimbLadder, boolean canModifyMoat, HashMap<AttackTarget, Boolean> targets, Armour armour, ArmourPenetration armourPenetration) {
        super(coordinate, owner, maxHitPoint, type, moveSpeed, defenceMultiplier, engineersCount);
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.canClimbLadder = canClimbLadder;
        this.canModifyMoat = canModifyMoat;
        this.targets = targets;
        this.armour = armour;
        this.armourPenetration = armourPenetration;
    }

    public void attack(){
    }

    public void stopAttack(){
    }
}
