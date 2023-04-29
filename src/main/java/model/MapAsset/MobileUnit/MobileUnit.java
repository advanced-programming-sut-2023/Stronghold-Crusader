package model.MapAsset.MobileUnit;

import model.Map.Cell;
import model.MapAsset.MapAsset;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

import java.util.ArrayList;

public class MobileUnit extends MapAsset {
    private int moveSpeed;
    private double defenceMultiplier;
    private int engineersCount;

    public MobileUnit(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    public MobileUnit(MobileUnit reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.moveSpeed = reference.moveSpeed;
        this.defenceMultiplier = reference.defenceMultiplier;
        this.engineersCount = reference.engineersCount;
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
