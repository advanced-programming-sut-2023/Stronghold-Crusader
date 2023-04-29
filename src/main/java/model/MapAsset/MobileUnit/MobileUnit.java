package model.MapAsset.MobileUnit;

import model.Map.Cell;
import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

import java.util.ArrayList;

public class MobileUnit extends MapAsset {
    private final int moveSpeed;
    private final double defenceMultiplier;
    private final int engineersCount;

    public MobileUnit(MobileUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.moveSpeed = reference.moveSpeed;
        this.defenceMultiplier = reference.defenceMultiplier;
        this.engineersCount = reference.engineersCount;
    }

    public void move(ArrayList<Cell> path) {
    }

    public void stopMovement() {
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }
}
