package model.MapAsset.MobileUnit;

import Settings.UnitConstants.AttackingUnitConstants;
import model.Player;
import utils.Vector2D;

public class AttackingUnitBuilder {
    private static AttackingUnit creatingAttackingUnit(Vector2D coordinate, Player owner, AttackingUnitConstants attackingUnitType) {
        AttackingUnit attackingUnitObject;
        switch (attackingUnitType) {
            case ASSASSIN:
                attackingUnitObject = new Assassin(coordinate, owner, attackingUnitType.getType());
                break;
            case FIRE_THROWER:
                attackingUnitObject = new FireThrower(coordinate, owner, attackingUnitType.getType());
                break;
            case CATAPULT:
                attackingUnitObject = new Catapult(coordinate, owner, attackingUnitType.getType());
                break;
            case FIRE_CATAPULT:
                attackingUnitObject = new FireCatapult(coordinate, owner, attackingUnitType.getType());
                break;
            case STABLE_CATAPULT:
                attackingUnitObject = new StableCatapult(coordinate, owner, attackingUnitType.getType());
                break;
            case KNIGHT:
                attackingUnitObject = new Knight(coordinate, owner, attackingUnitType.getType());
                break;
            case HORSE_ARCHER:
                attackingUnitObject = new HorseArcher(coordinate, owner, attackingUnitType.getType());
                break;
            default:
                attackingUnitObject = new AttackingUnit(coordinate, owner, attackingUnitType.getType());
                break;
        }
        setAttrs(attackingUnitObject, attackingUnitType);
        return attackingUnitObject;
    }

    //TODO change name
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
