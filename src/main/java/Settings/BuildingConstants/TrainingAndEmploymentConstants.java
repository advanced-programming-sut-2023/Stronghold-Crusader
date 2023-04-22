package Settings.BuildingConstants;

import model.enums.Material;
import model.enums.Weapon;

import java.util.ArrayList;
import java.util.List;

public enum TrainingAndEmploymentConstants {
    ;
    private int hitpoint;
    private int popularityChangeRate;
    private int rateOfProcess;
    private ArrayList<Weapon> weanponsNeeded;


    private TrainingAndEmploymentConstants(int hitpoint, int popularityChangeRate, int rateOfProcess, ArrayList<Weapon> weanponsNeeded) {
        this.hitpoint = hitpoint;
        this.popularityChangeRate = popularityChangeRate;
        this.rateOfProcess = rateOfProcess;
        this.weanponsNeeded = weanponsNeeded;
    }

    public int getHitpoint() {
        return hitpoint;
    }


}
