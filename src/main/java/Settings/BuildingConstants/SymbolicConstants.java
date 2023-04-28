package Settings.BuildingConstants;

import model.enums.MapAssetType;
import model.enums.Material;

import java.util.ArrayList;
import java.util.List;

public enum SymbolicConstants {
    //TODO @diba fill this
    ;
    private final int hitpoint;
    private final String changeType;
    private final int changeRate;
    private final MapAssetType type;

    private SymbolicConstants(int hitpoint, String changeType, int changeRate, MapAssetType type) {
        this.hitpoint = hitpoint;
        this.changeRate = changeRate;
        this.changeType = changeType;
        this.type = type;
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

    public MapAssetType getType() {
        return type;
    }
}
