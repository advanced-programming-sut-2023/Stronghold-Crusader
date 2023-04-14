package model;

import java.util.HashMap;

public class Assassin extends AttackingUnit{
    private boolean invisibility;
    public Assassin(Vector2D coordinate, Player owner, int maxHitPoint, String type, int moveSpeed, int defenceMultiplier, int engineersCount, int attackDamage, int attackRange, boolean canClimbLadder, boolean canModifyMoat, HashMap<AttackTarget, Boolean> targets, Armour armour, ArmourPenetration armourPenetration) {
        super(coordinate, owner, maxHitPoint, type, moveSpeed, defenceMultiplier, engineersCount, attackDamage, attackRange, canClimbLadder, canModifyMoat, targets, armour, armourPenetration);
    }

    public void setInvisibility(boolean invisibility) {
        this.invisibility = invisibility;
    }

    public boolean isInvisible() {
        return invisibility;
    }
}
