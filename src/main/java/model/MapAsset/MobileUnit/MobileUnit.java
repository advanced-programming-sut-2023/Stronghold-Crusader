package model.MapAsset.MobileUnit;

import model.Map.Cell;
import model.MapAsset.MapAsset;
import model.User.Player;
import utils.Vector2D;

import java.util.ArrayList;

public class MobileUnit extends MapAsset {
    private final int moveSpeed;
    private final double defenceMultiplier;
    private final int engineersCount;
    private final int cost;

    public MobileUnit(MobileUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.moveSpeed = reference.moveSpeed;
        this.defenceMultiplier = reference.defenceMultiplier;
        this.engineersCount = reference.engineersCount;
        this.cost = reference.cost;
    }

    public void move(ArrayList<Cell> path) {
    }

    public void stopMovement() {
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public double getDefenceMultiplier() {
        return defenceMultiplier;
    }

    public int getEngineersCount() {
        return engineersCount;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }

    @Override
    public String toString() {
        return super.toString() +
                ", moveSpeed=" + moveSpeed +
                ", engineersCount=" + engineersCount;
    }
}
