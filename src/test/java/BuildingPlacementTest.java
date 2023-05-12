import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import model.ConstantManager;
import model.Game.Game;
import model.Game.Governance;
import model.Map.Map;
import model.Map.MapManager;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.StorageBuilding;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.BuildingType;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.User.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Vector2D;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


public class BuildingPlacementTest {
    private static User user1, user2, user3;
    private static BuildingPlacementController controller;
    private static Field mapField, buildingCategory, currentPlayer;
    private static Game game;

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
        Player player2 = new Player(user2);
        Player player3 = new Player(user3);
        HashMap<Color, Player> players = new HashMap<>();
        players.put(Color.RED, player1);
        players.put(Color.BLUE, player2);
        players.put(Color.GREEN, player3);
        game = new Game(map, players, true);
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


    // TODO : failes for siege tent / storehouse  / drawBridge
    @Test
    public void buildingPlacementTest() throws IllegalAccessException {
        Player player = (Player) currentPlayer.get(controller);
        Governance governance = player.getGovernance();
        Map map = (Map) mapField.get(controller);

        Assertions.assertEquals(controller.dropBuilding("lookout_tower", 20, 20),
                BuildingPlacementMessage.SELECT_CATEGORY);

        controller.setBuildingCategory("defense_and_attack");

        StorageBuilding storeHouse = new StorageBuilding((StorageBuilding) ConstantManager.getInstance().getAsset(MapAssetType.STORE_HOUSE),
                new Vector2D(30, 30), player);
        governance.addAsset(storeHouse);
        map.addMapObject(new Vector2D(30, 30), storeHouse);

        ArrayList<MapAssetType> buildings = MapAssetType.getBuildings();
        ChangeEnvironmentController environmentController = new ChangeEnvironmentController(map, game);

        for (int i=0; i<buildings.size(); i++){
            MapAssetType type = buildings.get(i);
            if (type.equals(MapAssetType.SIEGE_TENT) || type.equals(MapAssetType.STORE_HOUSE) ||
                    type.equals(MapAssetType.DRAW_BRIDGE)) continue;
            Building reference = (Building) ConstantManager.getInstance().getAsset(type);
            Material needed = reference.getNeededMaterial();
            int neededAmount = reference.getNumberOfMaterialNeeded();
            BuildingCategory category = BuildingType.getCategory(type.name().toLowerCase());
            environmentController.clearCell(0,0);

            Assertions.assertNotNull(category);
            controller.setBuildingCategory(category.name().toLowerCase());
            Assertions.assertEquals(buildingCategory.get(controller), category);
            Assertions.assertNotEquals(controller.dropBuilding(type.name().toLowerCase(), 0, 0),
                    BuildingPlacementMessage.INVALID_BUILDING_TYPE);

            environmentController.setTexture(0,0,"shallow_water");
            Assertions.assertEquals(controller.dropBuilding(type.name().toLowerCase(), 0, 0),
                    BuildingPlacementMessage.INVALID_CELL_TYPE);

            environmentController.setTexture(0, 0, reference.getBuildingGroundType().get(0).name().toLowerCase());
            governance.changeStorageStock(needed, neededAmount);
            Assertions.assertEquals(governance.getStorageStock(needed), neededAmount);

            Assertions.assertTrue(governance.hasEnoughInStock(needed, neededAmount));
            Assertions.assertEquals(controller.dropBuilding(type.name().toLowerCase(), 0, 0),
                    BuildingPlacementMessage.BUILDING_DROP_SUCCESS);

            Assertions.assertEquals(controller.dropBuilding(type.name().toLowerCase(), 0, 0),
                    BuildingPlacementMessage.NOT_EMPTY);

            Assertions.assertEquals(governance.getStorageStock(needed), 0);
            Assertions.assertTrue(map.getCell(new Vector2D(0, 0)).containsType(type));
        }
    }

}
