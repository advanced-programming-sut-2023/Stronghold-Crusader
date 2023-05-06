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
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    private final ArrayList<AttackTarget> targets;
    private UnitState state;
    private MapAsset selectedAttackTarget;
    private MapAsset roundAttackTarget;
    private Vector2D selectedMoveDestination;
    private Vector2D roundMoveDestination;

    public AttackingUnit(AttackingUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.attackDamage = reference.attackDamage;
        this.attackRange = reference.attackRange;
        this.canClimbLadder = reference.canClimbLadder;
        this.canModifyMoat = reference.canModifyMoat;
        this.targets = reference.targets;
        this.weapon = reference.weapon;
        this.isAreaSplash = reference.isAreaSplash;
        state = UnitState.STANDING;
    }

    public void processNextRoundMove(Map map) {
        if (selectedMoveDestination != null) {
            roundMoveDestination = selectedMoveDestination;
            roundAttackTarget = null;
            return;
        }
        if (selectedAttackTarget != null) {
            if (coordinate.getDistance(selectedAttackTarget.getCoordinate()) <= attackRange) {
                roundMoveDestination = null;
                roundAttackTarget = selectedAttackTarget;
            } else {
                roundMoveDestination = roundAttackTarget.getCoordinate();
                roundAttackTarget = null;
            }
            return;
        }
        MapAsset attackTarget = findTarget(map, attackRange);
        if (attackTarget != null) {
            roundAttackTarget = attackTarget;
            roundMoveDestination = null;
            return;
        }
        MapAsset triggeredAttackTarget = findTarget(map, state.getTriggerRange());
        if (triggeredAttackTarget != null) {
            roundAttackTarget = null;
            roundMoveDestination = triggeredAttackTarget.getCoordinate();
            return;
        }
        roundMoveDestination = null;
        roundAttackTarget = null;
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

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public void selectAttackTarget(MapAsset target) {
        selectedAttackTarget = target;
        selectedMoveDestination = null;
    }

    public void selectMoveDestination(Vector2D dest) {
        selectedMoveDestination = dest;
        selectedAttackTarget = null;
    }

    public MapAsset getRoundAttackTarget() {
        return roundAttackTarget;
    }

    public Vector2D getRoundMoveDestination() {
        return roundMoveDestination;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", attack damage=" + attackDamage +
                ", attack range=" + attackRange +
                ", unit state=" + state.name().toLowerCase() +
                ", climbs ladder=" + canClimbLadder +
                ", modifies moat=" + canModifyMoat +
                ", weapon=" + weapon +
                ", targets=" + targets;
    }
}
