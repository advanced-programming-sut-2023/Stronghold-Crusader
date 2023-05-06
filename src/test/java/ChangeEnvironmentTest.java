import controller.MapControllers.ChangeEnvironmentController;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import model.User.User;
import model.enums.CellType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Vector2D;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.io.File;
import java.lang.reflect.Field;

public class ChangeEnvironmentTest {
    private static User user1, user2, user3;
    private static ChangeEnvironmentController controller;
    private static Field mapField;

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
        controller = new ChangeEnvironmentController(map);
        mapField = controller.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
    }

    @Test
    public void setTextureTest() throws IllegalAccessException {
        Assertions.assertEquals(controller.setTexture(10, 10, "invalid"),
                MapMakerMessage.INVALID_TEXTURE_TYPE);
        Assertions.assertEquals(controller.setTexture(200, 10, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(20, -20, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 10, "stone"),
                MapMakerMessage.SET_TEXTURE_SUCCESS);
        Vector2D coordinate = new Vector2D(10, 10);
        Assertions.assertEquals(((Map) mapField.get(controller)).getCell(coordinate).getType(),
                CellType.STONE);
    }

    @Test
    public void setTextureBlockTest() throws IllegalAccessException {
        Assertions.assertEquals(controller.setTexture(10, 10, "invalid"),
                MapMakerMessage.INVALID_TEXTURE_TYPE);
        Assertions.assertEquals(controller.setTexture(200, 10, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(20, -20, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 10, "stone"),
                MapMakerMessage.SET_TEXTURE_SUCCESS);
        Vector2D coordinate = new Vector2D(10, 10);
        Assertions.assertEquals(((Map) mapField.get(controller)).getCell(coordinate).getType(),
                CellType.STONE);
    }
}
