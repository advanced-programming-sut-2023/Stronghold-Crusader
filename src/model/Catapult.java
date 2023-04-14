package model;

import java.util.HashMap;

public class Catapult extends AttackingUnit{

    public Catapult(Vector2D coordinate, Player owner, int maxHitPoint, String type, int moveSpeed, int defenceMultiplier, int engineersCount, int attackDamage, int attackRange, boolean canClimbLadder, boolean canModifyMoat, HashMap<AttackTarget, Boolean> targets, Armour armour, ArmourPenetration armourPenetration) {
        super(coordinate, owner, maxHitPoint, type, moveSpeed, defenceMultiplier, engineersCount, attackDamage, attackRange, canClimbLadder, canModifyMoat, targets, armour, armourPenetration);
    }

    @Override
    public void attack() {
        super.attack();
    }
}
