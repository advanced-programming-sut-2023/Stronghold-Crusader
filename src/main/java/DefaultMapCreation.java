import model.ConstantManager;
import model.Map.Map;
import model.Map.MapManager;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.StorageBuilding;
import model.MapAsset.Tree;
import model.enums.AssetType.MapAssetType;
import model.enums.TreeType;
import utils.Vector2D;

public class DefaultMapCreation {
    public static void main(String[] args) {
        ConstantManager.load();
        Map map = new Map(new Vector2D(100, 100), "Desert");
        map.setPlayerCount(2);
        Vector2D coordinate = new Vector2D(2, 3);
        map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance().getAsset(MapAssetType.HEADQUARTER), coordinate, null));
        coordinate.y++;
        map.addMapObject(coordinate, new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE), coordinate, null));
        coordinate.x = 30;
        coordinate.y = 35;
        map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance().getAsset(MapAssetType.HEADQUARTER), coordinate, null));
        coordinate.x--;
        map.addMapObject(coordinate, new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE), coordinate, null));
        coordinate.y = 3;
        coordinate.x = 0;
        map.addMapObject(coordinate, new Tree((Tree) ConstantManager.getInstance().getAsset(MapAssetType.TREE), null, null, TreeType.CHERRY));
        MapManager.save(map, "1001");

        map = new Map(new Vector2D(50, 50), "Meadow");
        map.setPlayerCount(3);
        coordinate = new Vector2D(5, 5);
        map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance().getAsset(MapAssetType.HEADQUARTER), coordinate, null));
        coordinate.y++;
        map.addMapObject(coordinate, new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE), coordinate, null));
        coordinate.x = 20;
        coordinate.y = 7;
        map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance().getAsset(MapAssetType.HEADQUARTER), coordinate, null));
        coordinate.x++;
        map.addMapObject(coordinate, new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE), coordinate, null));
        coordinate.x = 10;
        coordinate.y = 18;
        map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance().getAsset(MapAssetType.HEADQUARTER), coordinate, null));
        coordinate.x--;
        map.addMapObject(coordinate, new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE), coordinate, null));

        MapManager.save(map, "1002");
    }
}
