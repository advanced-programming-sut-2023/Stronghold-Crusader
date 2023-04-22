package model.MapAsset.MobileUnit;

import Settings.UnitConstants.AttackingUnitConstants;
import model.Player;
import utils.Vector2D;

public class AttackingUnitBuilder {
    private static AttackingUnit creatingAttackingUnit(Vector2D coordinate, Player owner, AttackingUnitConstants attackingUnitType) {
        AttackingUnit attackingUnitObject = new AttackingUnit(coordinate, owner);
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

    public static AttackingUnit makeArcher(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.ARCHER);
    }

    public static AttackingUnit makeCrossbowman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.CROSSBOWMAN);
    }

    public static AttackingUnit makeSpearman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.SPEARMAN);

    }

    public static AttackingUnit makePikeman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.PIKEMAN);
    }

    public static AttackingUnit makeMaceman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.MACEMAN);
    }

    public static AttackingUnit makeSwordsman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.SWORDSMAN);
    }

    public static AttackingUnit makeBlackMonk(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.BLACK_MONK);
    }

    public static AttackingUnit makeArcherBow(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.ARCHER_BOW);
    }

    public static AttackingUnit makeSlave(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.SLAVE);
    }

    public static AttackingUnit makeSlinger(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.SLINGER);
    }

    public static AttackingUnit makeArabianSwordsman(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.ARABIAN_SWORDSMAN);
    }

    public static AttackingUnit makeBattleRam(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.BATTERING_RAM);
    }

    public static AttackingUnit makeDog(Vector2D coordinate, Player owner) {
        return creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.DOG);
    }

    public static Assassin makeAssassin(Vector2D coordinate, Player owner) {
        return (Assassin) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.ASSASSIN);
    }

    public static Catapult makeCatapult(Vector2D coordinate, Player owner) {
        return (Catapult) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.CATAPULT);
    }

    public static StableCatapult makeStableCatapult(Vector2D coordinate, Player owner) {
        return (StableCatapult) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.STABLE_CATAPULT);
    }

    public static FireCatapult makeFireCatapult(Vector2D coordinate, Player owner) {
        return (FireCatapult) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.FIRE_CATAPULT);

    }

    public static Knight makeKnight(Vector2D coordinate, Player owner) {
        return (Knight) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.KNIGHT);
    }

    public static HorseArcher makeHorseArcher(Vector2D coordinate, Player owner) {
        return (HorseArcher) creatingAttackingUnit(coordinate, owner, AttackingUnitConstants.HORSE_ARCHER);
    }

    public static FireThrower makeFireThrower(Vector2D coordinate, Player owner) {
        return (FireThrower) creatingAttackingUnit(coordinate,owner,AttackingUnitConstants.FIRE_THROWER);

    }
}
