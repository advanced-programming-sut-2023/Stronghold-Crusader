import controller.MapControllers.ChangeEnvironmentController;
import model.ConstantManager;
import model.Game.Game;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.MapAssetType;
import model.enums.CellType;
import model.enums.User.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Vector2D;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

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
        ConstantManager.load();
        Stronghold.getInstance().addUser(user1);
        Stronghold.getInstance().addUser(user2);
        Stronghold.getInstance().addUser(user3);
        Stronghold.getInstance().updateData();
        Map map = MapManager.load("1001");
        Player player1 = new Player(user1);
        Player player2 = new Player(user2);
        Player player3 = new Player(user3);
        HashMap<Color, Player> players = new HashMap<>();
        players.put(Color.RED, player1);
        players.put(Color.BLUE, player2);
        players.put(Color.GRAY, player3);
        Game game = new Game(map, players, true);
        controller = new ChangeEnvironmentController(map, game);
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
        Assertions.assertEquals(controller.setTexture(10, 10, 10, 10, "invalid"),
                MapMakerMessage.INVALID_TEXTURE_TYPE);
        Assertions.assertEquals(controller.setTexture(200, 10, 10, 10, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 200, 10, 10, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 10, 200, 10, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 10, 10, 200, "stone"),
                MapMakerMessage.INVALID_COORDINATE);
        Assertions.assertEquals(controller.setTexture(10, 10, 20, 20, "stone"),
                MapMakerMessage.SET_TEXTURE_SUCCESS);
        for (int i = 10; i <= 20; i++) {
            for (int j = 10; j <= 20; j++) {
                Vector2D coordinate = new Vector2D(i, j);
                Assertions.assertEquals(((Map) mapField.get(controller)).getCell(coordinate).getType(),
                        CellType.STONE);
            }
        }
    }

    @Test
    public void clearCellTest() {
        // TODO : add more tests after drop building is completely tested
    }

    @Test
    public void dropCliffTest() throws IllegalAccessException {
        // TODO : add not empty tests after drop building
        Assertions.assertEquals(controller.dropRock(10, 10, "invalid"),
                MapMakerMessage.INVALID_DIRECTION);

        Assertions.assertEquals(controller.dropRock(10, 10, "r"),
                MapMakerMessage.DROP_ROCK_SUCCESS);
        Vector2D coordinate1 = new Vector2D(10, 10);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate1).containsType(MapAssetType.CLIFF));

        Assertions.assertEquals(controller.dropRock(10, 10, "e"),
                MapMakerMessage.NOT_EMPTY);
        Vector2D coordinate2 = new Vector2D(10, 10);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate2).containsType(MapAssetType.CLIFF));

        Assertions.assertEquals(controller.dropRock(20, 20, "e"),
                MapMakerMessage.DROP_ROCK_SUCCESS);
        Vector2D coordinate3 = new Vector2D(20, 20);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate3).containsType(MapAssetType.CLIFF));

    }

    @Test
    public void dropTreeTest() throws IllegalAccessException {
        // TODO : add not empty tests after drop building
        Assertions.assertEquals(controller.dropTree(10, 10, "invalid"),
                MapMakerMessage.INVALID_TREE_TYPE);

        Assertions.assertEquals(controller.dropTree(15, 15, "olive"),
                MapMakerMessage.INVALID_CELL_TYPE);

        controller.setTexture(30, 30, CellType.PlAIN.getName());
        controller.setTexture(40, 40, CellType.PlAIN.getName());
        Assertions.assertEquals(controller.dropTree(30, 30, "olive"),
                MapMakerMessage.DROP_TREE_SUCCESS);
        Vector2D coordinate1 = new Vector2D(30, 30);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate1).containsType(MapAssetType.TREE));

        Assertions.assertEquals(controller.dropTree(30, 30, "olive"),
                MapMakerMessage.NOT_EMPTY);
        Vector2D coordinate2 = new Vector2D(30, 30);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate2).containsType(MapAssetType.TREE));

        Assertions.assertEquals(controller.dropTree(40, 40, "olive"),
                MapMakerMessage.DROP_TREE_SUCCESS);
        Vector2D coordinate3 = new Vector2D(40, 40);
        Assertions.assertTrue(((Map) mapField.get(controller)).getCell(coordinate3).containsType(MapAssetType.TREE));

    }
}
