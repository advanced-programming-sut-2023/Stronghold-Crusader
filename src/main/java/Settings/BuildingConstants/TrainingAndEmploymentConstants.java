package Settings.BuildingConstants;

import model.enums.MapAssetType;
import model.enums.Material;
import model.enums.People;
import model.enums.Weapon;

import java.util.ArrayList;
import java.util.List;

public enum TrainingAndEmploymentConstants {
    //TODO @diba fill this
    ;
    private final MapAssetType type;
    private int hitpoint;
    private int popularityChangeRate;
    private int rateOfProcess;
    private ArrayList<Weapon> weanponsNeeded;
    private ArrayList<People> peopleType;


    private TrainingAndEmploymentConstants(int hitpoint, int popularityChangeRate, int rateOfProcess,
                                           ArrayList<Weapon> weanponsNeeded, ArrayList<People> peopleType, MapAssetType type) {
        this.hitpoint = hitpoint;
        this.popularityChangeRate = popularityChangeRate;
        this.rateOfProcess = rateOfProcess;
        this.weanponsNeeded = weanponsNeeded;
        this.peopleType = peopleType;
        this.type = type;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public ArrayList<Weapon> getWeanponsNeeded() {
        return weanponsNeeded;
    }

    public int getPopularityChangeRate() {
        return popularityChangeRate;
    }

    public ArrayList<People> getPeopleType() {
        return peopleType;
    }

    public int getRateOfProcess() {
        return rateOfProcess;
    }

    public MapAssetType getType() {
        return type;
    }
}
