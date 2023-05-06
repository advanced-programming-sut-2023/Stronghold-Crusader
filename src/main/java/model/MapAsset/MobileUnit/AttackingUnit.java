package model.MapAsset.MobileUnit;

import model.MapAsset.MapAsset;
import model.User.Player;
import model.enums.AssetType.Material;
import model.enums.AssetType.UnitState;
import model.enums.AttackTarget;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    public final ArrayList<Material> weapon;
    private final int attackDamage;
    private final int attackRange;
    private final boolean isAreaSplash;
    private final boolean canClimbLadder;
    private final boolean canModifyMoat;
    private final HashMap<AttackTarget, Boolean> targets;
    private UnitState state;
    private MapAsset selectedAttackTarget;
    private Vector2D selectedMoveDestination;


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

    public ArrayList<Material> getWeapons() {
        return weapon;
    }

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    private MapAsset findTarget() {
        return null;
    }

    public void selectAttackTarget(MapAsset target) {
        selectedAttackTarget = target;
        selectedMoveDestination = null;
    }

    public void selectMoveDestination(Vector2D dest) {
        selectedMoveDestination = dest;
        selectedAttackTarget = null;
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
