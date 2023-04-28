package model.MapAsset.MobileUnit;

import Settings.UnitConstants.AttackingUnitConstants;
import model.Player;
import utils.Vector2D;

public class AttackingUnitBuilder {
    public static AttackingUnit creatingAttackingUnit(Vector2D coordinate, Player owner, AttackingUnitConstants attackingUnitType) {
        AttackingUnit attackingUnitObject;
        if (attackingUnitType == AttackingUnitConstants.ASSASSIN)
            attackingUnitObject = new Assassin(coordinate, owner, attackingUnitType.getType());
        else
            attackingUnitObject = new AttackingUnit(coordinate, owner, attackingUnitType.getType());
        setAttrs(attackingUnitObject, attackingUnitType);
        return attackingUnitObject;
    }

    private static void setAttrs(AttackingUnit attackingUnitObject, AttackingUnitConstants attackingUnitType) {
        attackingUnitObject.setDefenceMultiplier(attackingUnitType.getDefenseMultiplier());
        attackingUnitObject.setEngineersCount(attackingUnitType.getEngineerCount());
        attackingUnitObject.setMoveSpeed(attackingUnitType.getMoveSpeed());
        attackingUnitObject.setMaxHitPoint(attackingUnitType.getMaxHitPoint());
        attackingUnitObject.setAttackDamage(attackingUnitType.getAttackDamage());
        attackingUnitObject.setAttackRange(attackingUnitType.getAttackRange());
        attackingUnitObject.setCanClimbLadder(attackingUnitType.isCanClimbLadder());
        attackingUnitObject.setCanModifyMoat(attackingUnitType.isCanModifyMoat());
    }


}
