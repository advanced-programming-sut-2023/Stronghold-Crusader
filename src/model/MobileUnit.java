package model;

import java.util.ArrayList;

public class MobileUnit extends MapAsset{
    private int moveSpeed;
    private double defenceMultiplier;
    private int engineersCount;

    public MobileUnit(Vector2D coordinate, Player owner, int maxHitPoint, String type, int moveSpeed, int defenceMultiplier, int engineersCount) {
        super(coordinate, owner, maxHitPoint, type);
        this.moveSpeed = moveSpeed;
        this.defenceMultiplier = defenceMultiplier;
        this.engineersCount = engineersCount;
    }

    public void move(ArrayList<Cell> path){
    }

    public void stopMovement(){
    }
}
