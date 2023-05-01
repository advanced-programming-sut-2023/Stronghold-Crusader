package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import model.enums.People;
import utils.Vector2D;

import java.util.ArrayList;

public class TrainingAndEmploymentBuilding extends Building {
    private final ArrayList<People> peopleType;
    private final int popularityChangeRate;

    public TrainingAndEmploymentBuilding(TrainingAndEmploymentBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.peopleType = reference.peopleType;
        this.popularityChangeRate = reference.popularityChangeRate;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", peopleType=" + peopleType +
                ", popularityChangeRate=" + popularityChangeRate;
    }
}
