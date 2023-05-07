package model.MapAsset.MobileUnit;

import model.Map.Map;
import model.MapAsset.MapAsset;
import model.User.Player;
import utils.Vector2D;

import java.util.ArrayList;

public class MobileUnit extends MapAsset {
    private final int moveSpeed;
    private final double defenceMultiplier;
    private final int engineersCount;
    private final int cost;
    protected Vector2D finalMoveDestination;
    private Vector2D nextMoveDestination;

    public MobileUnit(MobileUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.moveSpeed = reference.moveSpeed;
        this.defenceMultiplier = reference.defenceMultiplier;
        this.engineersCount = reference.engineersCount;
        this.cost = reference.cost;
    }

    //returns true if reached the round move destination
    public boolean move() {
        coordinate.x = nextMoveDestination.x;
        coordinate.y = nextMoveDestination.y;
        if (coordinate.equals(finalMoveDestination)) {
            finalMoveDestination = null;
            return true;
        }
        return false;
    }

    public void findNextMoveDest(Map map) {
        if (finalMoveDestination == null) {
            nextMoveDestination = null;
            return;
        }
        ArrayList<Vector2D> traversePath = map.getTraversePath(this, finalMoveDestination);
        if (traversePath.isEmpty()) {
            nextMoveDestination = null;
            return;
        }
        nextMoveDestination = traversePath.get(Math.min(traversePath.size() - 1, moveSpeed));
    }

    public boolean hasNextMoveDestination() {
        return nextMoveDestination != null;
    }

    public void selectMoveDestination(Vector2D dest) {
        finalMoveDestination = dest;
    }

    public int getEngineersCount() {
        return engineersCount;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void takeDamageFrom(AttackingUnit attacker) {
        hitPoint -= attacker.getAttackDamage() * defenceMultiplier;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", moveSpeed=" + moveSpeed +
                ", engineersCount=" + engineersCount;
    }
}
