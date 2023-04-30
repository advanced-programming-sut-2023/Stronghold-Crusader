package model.Map;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.Player;
import model.enums.CellType;
import model.enums.MapAssetType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collection;

public class Cell {
    private final Vector2D coordinate;
    private CellType type;
    private ArrayList<MapAsset> objects;

    public Cell(Vector2D coordinate, CellType type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public Collection<MapAsset> getAllObjects() {
        return null;
    }

    public Collection<MapAsset> getPlayersObjects(Player player) {
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
    public void removeNonSavableAssets(){
        ArrayList<MapAsset> newObjects = new ArrayList<>();
        for (MapAsset asset : objects) {
            MapAssetType type = asset.getType();
            if(type == MapAssetType.TREE || type == MapAssetType.CLIFF || type == MapAssetType.HEADQUARTER){
                asset.setOwner(null);
                newObjects.add(asset);
            }
        }
        objects = newObjects;
    }

    public boolean isTraversable(MobileUnit unit) {
        return false;
    }

    public void clear(){

    }

    public void addMapAsset(MapAsset obj){
        objects.add(obj);
    }
    public void removeMapAsset(MapAsset obj){objects.remove(obj);}
    public boolean isThereUnit(){
        for (MapAsset mapAsset: objects) {
            if (mapAsset instanceof MobileUnit) return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return objects.isEmpty();
    }
}
