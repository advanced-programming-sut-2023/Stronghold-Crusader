package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;

import java.util.ArrayList;

public class TrainingAndEmploymentBuilding extends Building {
    private final ArrayList<MapAssetType> peopleType;
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
