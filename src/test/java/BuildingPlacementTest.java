import controller.MapControllers.BuildingPlacementController;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.BuildingCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

import java.io.File;
import java.lang.reflect.Field;

public class BuildingPlacementTest {
    private static User user1, user2, user3;
    private static BuildingPlacementController controller;
    private static Field mapField, buildingCategory;

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
        Stronghold.getInstance().addUser(user1);
        Stronghold.getInstance().addUser(user2);
        Stronghold.getInstance().addUser(user3);
        Stronghold.getInstance().updateData();
        Map map = MapManager.load("1001");
        Player player1 = new Player(user1);
        controller = new BuildingPlacementController(player1, map);
        mapField = controller.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
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

        // TODO change
        Assertions.assertEquals(controller.setBuildingCategory("store"),
                BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS);
        Assertions.assertEquals(buildingCategory.get(controller), BuildingCategory.NORMAL);
    }


}
