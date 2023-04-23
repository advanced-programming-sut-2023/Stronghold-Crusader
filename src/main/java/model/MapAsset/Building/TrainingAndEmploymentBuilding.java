package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import model.enums.People;
import utils.Vector2D;

import java.util.ArrayList;

public class TrainingAndEmploymentBuilding extends Building{
    private ArrayList<People> peopleType;
    private int popularityChangeRate;

    public TrainingAndEmploymentBuilding(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }

    public void setPeopleType(ArrayList<People> peopleType) {
        this.peopleType = peopleType;
    }

    public void setPopularityChangeRate(int popularityChangeRate) {
        this.popularityChangeRate = popularityChangeRate;
    }
}
