package Settings.UnitConstants;

import model.enums.AttackTarget;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;

import java.util.ArrayList;
import java.util.List;

public enum AttackingUnitConstants {
    DOG(1, 8, 0, 70, 80, 1, false,
            MapAssetType.DOG, 100, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    //European Units :
    ARCHER(1, 8, 0, 500, 20, 3, true,
            MapAssetType.ARCHER, 30, new ArrayList<>(List.of(Material.BOW)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    CROSSBOWMAN(0.8, 3, 0, 800, 50, 3, false,
            MapAssetType.CROSSBOWMAN, 60, new ArrayList<>(List.of(Material.BOW, Material.ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    SPEARMAN(1, 5, 0, 600, 100, 1, true,
            MapAssetType.SPEARMAN, 30, new ArrayList<>(List.of(Material.SPEAR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    PIKEMAN(0.6, 3, 0, 1700, 250, 1, false,
            MapAssetType.PIKEMAN, 60, new ArrayList<>(List.of(Material.PIKE)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    MACEMAN(0.8, 6, 0, 1500, 175, 1, true,
            MapAssetType.MACEMAN, 90, new ArrayList<>(List.of(Material.MACE, Material.ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    SWORDSMAN(0.5, 5, 0, 2000, 350, 1, false,
            MapAssetType.SWORDSMAN, 100, new ArrayList<>(List.of(Material.SWORD, Material.ARMOUR)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    KNIGHT(0.5, 3, 0, 2000, 300, 1, false,
            MapAssetType.KNIGHT, 120, new ArrayList<>(List.of(Material.SWORD, Material.ARMOUR, Material.HORSE)),
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    TUNNELER(0.9, 10, 0, 100, 10, 1, false,
            MapAssetType.TUNNELER, 10, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    BLACK_MONK(1, 3, 0, 500, 50, 1, false,
            MapAssetType.BLACK_MONK, 15, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    //Arabian Units:
    ARCHER_BOW(1, 8, 0, 500, 20, 3, false,
            MapAssetType.ARCHER_BOW, 60, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    SLAVE(1, 8, 0, 30, 20, 1, false,
            MapAssetType.SLAVE, 5, null,
            new ArrayList<>(List.of(AttackTarget.HOUSE)), false),

    SLINGER(1, 8, 0, 150, 20, 2, false,
            MapAssetType.SLINGER, 10, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    ASSASSIN(1, 5, 0, 1000, 100, 1, false,
            MapAssetType.ASSASSIN, 60, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    HORSE_ARCHER(1, 10, 0, 1000, 50, 3, false,
            MapAssetType.HORSE_ARCHER, 80, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    ARABIAN_SWORDSMAN(0.6, 5, 0, 1500, 275, 1, false,
            MapAssetType.ARABIAN_SWORDSMAN, 85, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), false),
    FIRE_THROWER(1, 8, 0, 800, 100, 2, false,
            MapAssetType.FIRE_THROWER, 90, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS, AttackTarget.HOUSE)), false),
    //Siege warfare:
    BATTERING_RAM(1, 3, 4, 400, 500, 1, false,
            MapAssetType.BATTERING_RAM, 20, null,
            new ArrayList<>(List.of(AttackTarget.BUILDING)), false),
    CATAPULT(1, 3, 2, 400, 400, 3, false,
            MapAssetType.CATAPULT, 40, null,
            new ArrayList<>(List.of(AttackTarget.BUILDING)), true),
    STABLE_CATAPULT(1, 0, 3, 500, 500, 4, false,
            MapAssetType.STABLE_CATAPULT, 50, null,
            new ArrayList<>(List.of(AttackTarget.BUILDING)), true),
    FIRE_CATAPULT(1, 3, 2, 400, 300, 2, false,
            MapAssetType.FIRE_CATAPULT, 100, null,
            new ArrayList<>(List.of(AttackTarget.TROOPS)), true);

    public final double maxHitPoint;
    public final MapAssetType type;
    public final int cost;
    public final int moveSpeed;
    public final double defenceMultiplier;
    public final int engineersCount;
    public final boolean canClimbLadder;
    public final ArrayList<Material> weapon;
    public final int attackDamage;
    public final int attackRange;
    public final boolean isAreaSplash;
    public final ArrayList<AttackTarget> targets;

    AttackingUnitConstants(double defenceMultiplier, int moveSpeed, int engineersCount, double maxHitPoint, int attackDamage
            , int attackRange, boolean canClimbLadder, MapAssetType type,
                           int cost, ArrayList<Material> weapon, ArrayList<AttackTarget> targets, boolean isAreaSplash) {
        this.maxHitPoint = maxHitPoint;
        this.type = type;
        this.cost = cost;
        this.moveSpeed = moveSpeed;
        this.defenceMultiplier = defenceMultiplier;
        this.engineersCount = engineersCount;
        this.canClimbLadder = canClimbLadder;
        this.weapon = weapon;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.isAreaSplash = isAreaSplash;
        this.targets = targets;
    }
}
