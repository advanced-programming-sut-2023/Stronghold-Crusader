package model.MapAsset.MobileUnit;

import model.MapAsset.MapAsset;
import model.Player;
import model.enums.AttackTarget;
import model.enums.MapAssetType;
import model.enums.Weapon;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;

public class AttackingUnit extends MobileUnit {
    private int attackDamage;
    private int attackRange;
    private boolean canClimbLadder;
    private boolean canModifyMoat;
    public ArrayList<Weapon> weapon;
    private HashMap<AttackTarget, Boolean> targets;

    public AttackingUnit(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setCanClimbLadder(boolean canClimbLadder) {
        this.canClimbLadder = canClimbLadder;
    }

    public void setCanModifyMoat(boolean canModifyMoat) {
        this.canModifyMoat = canModifyMoat;
    }

    public void setWeapon(ArrayList<Weapon> weapon) {
        this.weapon = weapon;
    }

    public void setTargets(HashMap<AttackTarget, Boolean> targets) {
        this.targets = targets;
    }

    public void attack() {
    }

    public void stopAttack() {
    }
}
