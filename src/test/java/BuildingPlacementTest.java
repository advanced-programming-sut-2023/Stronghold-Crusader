import controller.MapControllers.BuildingPlacementController;
import model.ConstantManager;
import model.Game.Game;
import model.Game.Governance;
import model.Map.Map;
import model.Map.MapManager;
import model.MapAsset.Building.StorageBuilding;
import model.MapAsset.MapAsset;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Vector2D;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Currency;

public class BuildingPlacementTest {
    private static User user1, user2, user3;
    private static BuildingPlacementController controller;
    private static Field mapField, buildingCategory, currentPlayer;

    @BeforeAll
    public static void beforeAll() throws NoSuchFieldException {
        File users = new File("Resources/users.user");
        users.delete();
        user1 = new User("dibaHadiEsfangereh", "Hadie83@",
                "dibahadie@gmail.com", "dibaH", "someSlogan");
        user2 = new User("dibaHadiEsfangereh2", "Hadie83@",
                "dibahadie2@gmail.com", "dibaH2", "someSlogan");
        user3 = new User("dibaHadiEsfangereh3", "Hadie83@",
                "dibahadie3@gmail.com", "dibaH3", "someSlogan");
        Stronghold.load();
        ConstantManager.load();
        Stronghold.getInstance().addUser(user1);
        Stronghold.getInstance().addUser(user2);
        Stronghold.getInstance().addUser(user3);
        Stronghold.getInstance().updateData();
        Map map = MapManager.load("1002");
        Player player1 = new Player(user1);
        controller = new BuildingPlacementController(player1, map);
        mapField = controller.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        currentPlayer = controller.getClass().getDeclaredField("currentPlayer");
        currentPlayer.setAccessible(true);
        buildingCategory = controller.getClass().getDeclaredField("buildingCategory");
        buildingCategory.setAccessible(true);
    }

    @Test
    public void testSetBuildingCategory() throws IllegalAccessException {
        Assertions.assertEquals(controller.setBuildingCategory("invalid"),
                BuildingPlacementMessage.INVALID_BUILDING_CATEGORY);
        Assertions.assertNull(buildingCategory.get(controller));

        Assertions.assertEquals(controller.setBuildingCategory("defense_and_attack"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.DEFENSE_AND_ATTACK);

        Assertions.assertEquals(controller.setBuildingCategory("production"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.PRODUCTION);

        Assertions.assertEquals(controller.setBuildingCategory("training_and_employment"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.TRAINING_AND_EMPLOYMENT);

        Assertions.assertEquals(controller.setBuildingCategory("storage"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.STORAGE);

        Assertions.assertEquals(controller.setBuildingCategory("normal"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.NORMAL);

        Assertions.assertEquals(controller.setBuildingCategory("entrance"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.ENTRANCE);
    }

    @Test
    public void buildingPlacementTest() throws IllegalAccessException {
        Player player = (Player) currentPlayer.get(controller);
        Governance governance = player.getGovernance();
        Map map = (Map) mapField.get(controller);


        StorageBuilding storeHouse = new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE),
                new Vector2D(30, 30), player);
        governance.addAsset(storeHouse);
        map.addMapObject(new Vector2D(30, 30), storeHouse);
        governance.changeStorageStock(Material.WOOD, 100);

        Assertions.assertEquals(governance.getStorageStock(Material.WOOD), 100);
        Assertions.assertTrue(governance.hasEnoughInStock(Material.WOOD, 10));
        Assertions.assertEquals(controller.dropBuilding("lookout_tower", 20, 20),
                BuildingPlacementMessage.BUILDING_DROP_SUCCESS);
        Assertions.assertEquals(governance.getStorageStock(Material.WOOD), 0);
        Assertions.assertTrue(map.getCell(new Vector2D(20, 20)).containsType(MapAssetType.LOOKOUT_TOWER));


//        Assertions.assertEquals(controller.dropBuilding("lookout_tower", 10000, 10000),
//                BuildingPlacementMessage.INVALID_COORDINATE);
//        Assertions.assertEquals(controller.dropBuilding("lookout_tower", 10, 10),
//                BuildingPlacementMessage.NOT_EMPTY);
//        Assertions.assertEquals(controller.dropBuilding("invalid", 10, 10),
//                BuildingPlacementMessage.INVALID_BUILDING_TYPE);

    }

}
