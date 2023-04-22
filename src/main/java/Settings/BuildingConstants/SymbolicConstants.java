package Settings.BuildingConstants;

import model.enums.Material;

import java.util.ArrayList;
import java.util.List;

public enum SymbolicConstants {
    ;
    private int hitpoint;
    private String changeType;
    private int changeRate;

    private SymbolicConstants(int hitpoint, String changeType, int changeRate) {
        this.hitpoint = hitpoint;
        this.changeRate = changeRate;
        this.changeType = changeType;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getChangeRate() {
        return changeRate;
    }

    public String getChangeType() {
        return changeType;
    }
}
