package model.enums;

import model.enums.AssetType.MapAssetType;

import java.util.ArrayList;
import java.util.List;

import static model.enums.AssetType.MapAssetType.*;

public enum AttackTarget {
    //TODO fill these
    BUILDING(new ArrayList<>(List.of(WALL))),
    HOUSE(new ArrayList<>(List.of(INN))),
    TROOPS(new ArrayList<>(List.of(ARCHER)));
    private final ArrayList<MapAssetType> items;

    AttackTarget(ArrayList<MapAssetType> targets) {
        this.items = targets;
    }

    public ArrayList<MapAssetType> getItems() {
        return items;
    }
}
