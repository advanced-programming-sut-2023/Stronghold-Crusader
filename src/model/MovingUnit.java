package model;

import java.util.ArrayList;

public class MovingUnit extends MapAsset{
    private int moveSpeed;
    private double defenceMultiplier;

    public MovingUnit(Vector2D coordinate, Player owner) {
        super(coordinate, owner);
    }

    public void move(ArrayList<Cell> path){
    }
}
