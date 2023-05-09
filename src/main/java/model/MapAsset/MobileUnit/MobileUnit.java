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
    private final boolean canClimbLadder;
    protected Vector2D finalMoveDestination;
    private Vector2D nextMoveDestination;
    private Vector2D[] petrolPath;

    public MobileUnit(MobileUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.moveSpeed = reference.moveSpeed;
        this.defenceMultiplier = reference.defenceMultiplier;
        this.engineersCount = reference.engineersCount;
        this.canClimbLadder = reference.canClimbLadder;
    }

    public void move() {
        coordinate.x = nextMoveDestination.x;
        coordinate.y = nextMoveDestination.y;
        if (coordinate.equals(finalMoveDestination)) {
            if (petrolPath == null)
                finalMoveDestination = null;
            else {
                if (petrolPath[0].equals(coordinate))
                    finalMoveDestination = petrolPath[1];
                else
                    finalMoveDestination = petrolPath[0];
            }
        }
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
        petrolPath = null;
    }

    public void selectPetrolPath(Vector2D v1, Vector2D v2) {
        petrolPath = new Vector2D[2];
        petrolPath[0] = new Vector2D(v1.x, v1.y);
        petrolPath[1] = new Vector2D(v2.x, v2.y);
        finalMoveDestination = petrolPath[0];
    }

    public int getEngineersCount() {
        return engineersCount;
    }

    public boolean canClimbLadder() {
        return canClimbLadder;
    }

    @Override
    public void takeDamageFrom(AttackingUnit attacker) {
        hitPoint -= attacker.getAttackDamage() * defenceMultiplier;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", moveSpeed=" + moveSpeed +
                ", climbs ladder=" + canClimbLadder +
                ", engineersCount=" + engineersCount;
    }
}
