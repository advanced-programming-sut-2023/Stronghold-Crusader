package model.MapAsset.MobileUnit;

import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.MapAsset;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.AssetType.UnitState;
import model.enums.AttackTarget;
import utils.Vector2D;

import java.util.ArrayList;

public class AttackingUnit extends MobileUnit {
    public final ArrayList<Material> weapon;
    private final int attackDamage;
    private final int attackRange;
    private final boolean isAreaSplash;
    private final ArrayList<AttackTarget> targets;
    private UnitState state;
    private MapAsset selectedAttackTarget;
    private MapAsset nextRoundAttackTarget;

    public AttackingUnit(AttackingUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.attackDamage = reference.attackDamage;
        this.attackRange = reference.attackRange;
        this.targets = reference.targets;
        this.weapon = reference.weapon;
        this.isAreaSplash = reference.isAreaSplash;
        state = UnitState.STANDING;
    }

    public void processNextRoundMove(Map map) {
        checkForTargetDeath();
        if (finalMoveDestination != null) {
            nextRoundAttackTarget = null;
            return;
        }
        if (selectedAttackTarget != null) {
            if (coordinate.getDistance(selectedAttackTarget.getCoordinate()) <= attackRange) {
                finalMoveDestination = null;
                nextRoundAttackTarget = selectedAttackTarget;
            } else {
                finalMoveDestination = nextRoundAttackTarget.getCoordinate();
                nextRoundAttackTarget = null;
            }
            return;
        }
        MapAsset attackTarget = findTarget(map, attackRange);
        if (attackTarget != null) {
            nextRoundAttackTarget = attackTarget;
            finalMoveDestination = null;
            return;
        }
        MapAsset triggeredAttackTarget = findTarget(map, state.getTriggerRange());
        if (triggeredAttackTarget != null) {
            nextRoundAttackTarget = null;
            finalMoveDestination = triggeredAttackTarget.getCoordinate();
            return;
        }
        nextRoundAttackTarget = null;
        finalMoveDestination = null;
    }

    private void checkForTargetDeath() {
        if (selectedAttackTarget != null && selectedAttackTarget.getHitPoint() < 0)
            selectedAttackTarget = null;
    }

    private MapAsset findTarget(Map map, int range) {
        ArrayList<Cell> inRangeCells = map.getNearbyCells(coordinate, range);
        for (Cell cell : inRangeCells) {
            for (MapAsset asset : cell.getAllAssets()) {
                if (!asset.getOwner().equals(owner))
                    continue;
                MapAssetType enemyType = asset.getType();
                for (AttackTarget target : targets) {
                    for (MapAssetType assetType : target.getItems()) {
                        if (enemyType == assetType)
                            return asset;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Material> getWeapons() {
        return weapon;
    }

    @Override
    public void takeDamageFrom(AttackingUnit attacker) {
        if (selectedAttackTarget == null)
            selectedAttackTarget = attacker;
        super.takeDamageFrom(attacker);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public void selectAttackTarget(MapAsset target) {
        selectedAttackTarget = target;
        super.selectMoveDestination(null);
    }

    public void selectMoveDestination(Vector2D dest) {
        selectedAttackTarget = null;
        super.selectMoveDestination(dest);
    }

    @Override
    public void selectPatrolPath(Vector2D v1, Vector2D v2) {
        selectedAttackTarget = null;
        super.selectPatrolPath(v1, v2);
    }

    public MapAsset getNextRoundAttackTarget() {
        if(nextRoundAttackTarget == null)
            return null;
        if (nextRoundAttackTarget.getHitPoint() < 0)
            return null;
        return nextRoundAttackTarget;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", attack damage=" + attackDamage +
                ", attack range=" + attackRange +
                ", unit state=" + state.name().toLowerCase() +
                ", weapon=" + weapon +
                ", targets=" + targets;
    }
}
