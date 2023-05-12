import controller.MapControllers.MapSelectController;
import model.Map.Map;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.User.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

import static view.enums.messages.MapMessage.MapSelectMessage.*;

public class MapSelectTest {
    private static User user1;
    private static User user2;
    private static User user3;
    private static MapSelectController controller;

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
    }

    @Test
    public void selectMap() throws NoSuchFieldException, IllegalAccessException {
        controller = new MapSelectController(user1);

        Field selectedMap = controller.getClass().getDeclaredField("selectedMap");
        selectedMap.setAccessible(true);
        Field isMapModifiable = controller.getClass().getDeclaredField("isMapModifiable");
        isMapModifiable.setAccessible(true);
        Field players = controller.getClass().getDeclaredField("players");
        players.setAccessible(true);


        Assertions.assertEquals(controller.selectMap("1000", true), INVALID_MAP_ID);
        Assertions.assertNull(selectedMap.get(controller));
        Assertions.assertFalse(isMapModifiable.getBoolean(controller));
        Assertions.assertEquals(controller.startGame(), MAP_NOT_SELECTED);
        Assertions.assertEquals(controller.addPlayer("diba", "red"), MAP_NOT_SELECTED);


        Assertions.assertEquals(controller.selectMap("1002", true), MAP_SELECT_SUCCESS);
        Assertions.assertNotNull(selectedMap.get(controller));
        Assertions.assertTrue(isMapModifiable.getBoolean(controller));
        Assertions.assertEquals(((Map) selectedMap.get(controller)).getPlayerCount(), 3);
        Assertions.assertEquals(controller.startGame(), NOT_ENOUGH_PLAYERS);

        Assertions.assertEquals(controller.addPlayer("diba", "red"), USERNAME_INVALID);
        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh", "white"), INVALID_COLOR);

        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh", "red"), PLAYER_ADD_SUCCESS);
        Assertions.assertEquals(((HashMap<Color, Player>) players.get(controller)).get(Color.RED).getUsername(),
                "dibaHadiEsfangereh");
        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh", "blue"), PLAYER_EXISTS);
        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh2", "red"), PICKED_COLOR);
        Assertions.assertEquals(controller.startGame(), NOT_ENOUGH_PLAYERS);

        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh2", "blue"),
                PLAYER_ADD_SUCCESS);
        Assertions.assertEquals(controller.startGame(), NOT_ENOUGH_PLAYERS);
        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh3", "green"),
                PLAYER_ADD_SUCCESS);
        Assertions.assertEquals(controller.addPlayer("dibaHadiEsfangereh2", "red"),
                PLAYER_COUNT_EXCEEDED);
        Assertions.assertEquals(controller.startGame(), GAME_CREATION_SUCCESS);

    }
}
