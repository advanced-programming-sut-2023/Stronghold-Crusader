package Settings.UnitConstants;

import model.enums.AttackTarget;
import model.enums.MapAssetType;
import model.enums.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum AttackingUnitConstants {
    DOG(1, 8, 0, 70, 80, 1, false
            , false, MapAssetType.DOG, 100, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    //European Units :
    ARCHER(1, 8, 0, 500, 20, 3, true,
            true, MapAssetType.ARCHER, 30, new ArrayList<>(List.of(Weapon.BOW)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    CROSSBOWMAN(0.8, 3, 0, 800, 50, 2, false,
            false, MapAssetType.CROSSBOWMAN, 60, new ArrayList<>(List.of(Weapon.BOW, Weapon.LEATHER_ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    SPEARMAN(1, 5, 0, 600, 100, 1, true,
            true, MapAssetType.SPEARMAN, 30, new ArrayList<>(List.of(Weapon.SPEAR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    PIKEMAN(0.6, 3, 0, 1700, 250, 1, false,
            true, MapAssetType.PIKEMAN, 60, new ArrayList<>(List.of(Weapon.PIKE)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    MACEMAN(0.8, 6, 0, 1500, 175, 1, true,
            true, MapAssetType.MACEMAN, 90, new ArrayList<>(List.of(Weapon.MACE, Weapon.METAL_ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    SWORDSMAN(0.5, 1, 0, 2000, 350, 1, false,
            false, MapAssetType.SWORDSMAN, 100, new ArrayList<>(List.of(Weapon.SWORD, Weapon.METAL_ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    KNIGHT(0.5, 10, 0, 2000, 300, 1, false,
            false, MapAssetType.KNIGHT, 120, new ArrayList<>(List.of(Weapon.SWORD, Weapon.METAL_ARMOUR, Weapon.HORSE)),
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    //TODO fill this
    TUNNELER(0, 0, 0, 0, 0, 0, false,
            false, MapAssetType.TUNNELER, 10, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    BLACK_MONK(1, 3, 0, 500, 50, 1, false,
            false, MapAssetType.BLACK_MONK, 15, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    //Arabian Units:
    ARCHER_BOW(1, 8, 0, 500, 20, 3, false,
            true, MapAssetType.ARCHER_BOW, 60, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    SLAVE(1, 8, 0, 100, 20, 1, false,
            true, MapAssetType.SLAVE, 5, null,
            new ArrayList<>(List.of(AttackTarget.HOUSE))),

    SLINGER(1, 8, 0, 150, 20, 2, false,
            false, MapAssetType.SLINGER, 10, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    ASSASSIN(1, 5, 0, 1000, 100, 1, false,
            false, MapAssetType.ASSASSIN, 60, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    HORSE_ARCHER(1, 10, 0, 1000, 50, 3, false,
            false, MapAssetType.HORSE_ARCHER, 80, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    ARABIAN_SWORDSMAN(0.6, 5, 0, 1500, 275, 1, false,
            false, MapAssetType.ARABIAN_SWORDSMAN, 85, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    FIRE_THROWER(1, 8, 0, 800, 100, 2, false,
            false, MapAssetType.FIRE_THROWER, 90, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS, AttackTarget.HOUSE))),
    //Siege warfare:
    BATTERING_RAM(1, 3, 4, 400, 500, 1, false,
            false, MapAssetType.BATTERING_RAM, 20, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS))),
    CATAPULT(1, 3, 2, 400, 400, 3, false,
            false, MapAssetType.CATAPULT, 40, null,
            new ArrayList<>(List.of(AttackTarget.BUILDING))),
    STABLE_CATAPULT(1, 0, 3, 500, 500, 4, false,
            false, MapAssetType.STABLE_CATAPULT, 50, null,
            new ArrayList<>(List.of(AttackTarget.BUILDING))),
    FIRE_CATAPULT(1, 3, 2, 400, 300, 2, false,
            false, MapAssetType.FIRE_CATAPULT, 100, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)));
    private final double defenseMultiplier;
    private final int moveSpeed;
    private final int engineerCount;
    private final double maxHitPoint;
    private final int attackDamage;
    private final int attackRange;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    private final MapAssetType type;
    private final int cost;
    private final ArrayList<Weapon> weapon;
    private final ArrayList<AttackTarget> targets;

    AttackingUnitConstants(double defenseMultiplier, int moveSpeed, int engineerCount, double maxHitPoint, int attackDamage
            , int attackRange, boolean canClimbLadder, boolean canModifyMoat, MapAssetType type,
                           int cost, ArrayList<Weapon> weapon, ArrayList<AttackTarget> targets) {
        this.defenseMultiplier = defenseMultiplier;
        this.moveSpeed = moveSpeed;
        this.engineerCount = engineerCount;
        this.maxHitPoint = maxHitPoint;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.canClimbLadder = canClimbLadder;
        this.canModifyMoat = canModifyMoat;
        this.type = type;
        this.cost = cost;
        this.weapon = weapon;
        this.targets = targets;
    }

    public double getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getEngineerCount() {
        return engineerCount;
    }

    public double getMaxHitPoint() {
        return maxHitPoint;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isCanClimbLadder() {
        return canClimbLadder;
    }

    public boolean isCanModifyMoat() {
        return canModifyMoat;
    }

    public MapAssetType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Weapon> getWeapon() {
        return weapon;
    }
}
