package model.Map;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.enums.CellType;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collection;

public class Cell {
    private final Vector2D coordinate;
    private CellType type;
    private ArrayList<MapAsset> assets;

    public Cell(Vector2D coordinate, CellType type) {
        this.coordinate = coordinate;
        this.type = type;
        assets = new ArrayList<>();
    }

    public Collection<MapAsset> getAllAssets() {
        return assets;
    }

    public Collection<MapAsset> getAllAssets(Player player) {
        return null;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Vector2D getCoordinate() {
        return coordinate;
    }

    //only gets called at the end of the game to prepare Map for saving.
    public void removeNonSavableAssets() {
        ArrayList<MapAsset> newObjects = new ArrayList<>();
        for (MapAsset asset : assets) {
            MapAssetType type = asset.getType();
            if (type == MapAssetType.TREE || type == MapAssetType.CLIFF || type == MapAssetType.HEADQUARTER) {
                asset.setOwner(null);
                newObjects.add(asset);
            }
        }
        assets = newObjects;
    }

    public boolean isTraversable(MobileUnit unit) {
        return false;
    }

    public void clear() {

    }

    public void addMapAsset(MapAsset obj) {
        assets.add(obj);
    }

    public void removeMapAsset(MapAsset obj) {
        assets.remove(obj);
    }

    public boolean isThereUnit() {
        for (MapAsset mapAsset : assets) {
            if (mapAsset instanceof MobileUnit) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return assets.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Cell type: " + type.getName());
        for (MapAsset asset : assets) {
            output.append('\n').append(asset.toString());
        }
        return output.toString();
    }
}
