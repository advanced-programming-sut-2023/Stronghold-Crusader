package model;

import utils.Vector2D;

import java.util.ArrayList;

public class MobileUnit extends MapAsset {
    private int moveSpeed;
    private double defenceMultiplier;
    private int engineersCount;

    public MobileUnit(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setDefenceMultiplier(double defenceMultiplier) {
        this.defenceMultiplier = defenceMultiplier;
    }

    public void setEngineersCount(int engineersCount) {
        this.engineersCount = engineersCount;
    }

    public void move(ArrayList<Cell> path) {
    }

    public void stopMovement() {
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
