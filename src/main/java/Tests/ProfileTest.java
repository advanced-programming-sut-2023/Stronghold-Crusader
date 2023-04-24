package Tests;

import Settings.Settings;
import controller.ProfileController;
import model.Stronghold;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.enums.messages.ProfileMessages;

import java.io.File;

public class ProfileTest {
    @Test
    public void testChangeUsername() {
        File users = new File(Settings.USERS_PATH);
        users.delete();
        User user = new User("dibaHadiEsfangereh", "Hadie83@",
                "dibahadie@gmail.com", "dibaH", "someSlogan");
        User user2 = new User("dibaHadiEsfangereh2", "Hadie83@",
                "dibahadie2@gmail.com", "dibaH2", "someSlogan");
        Stronghold.load();
        Stronghold.getInstance().addUser(user);
        Stronghold.getInstance().addUser(user2);
        ProfileController controller = new ProfileController(user);

        ProfileMessages msg = controller.changeUsername("diba@hadi");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_USERNAME_FORMAT);
        Assertions.assertEquals(user.getUsername(), "dibaHadiEsfangereh");

        msg = controller.changeUsername("diba  hadi");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_USERNAME_FORMAT);
        Assertions.assertEquals(user.getUsername(), "dibaHadiEsfangereh");

        msg = controller.changeUsername("diba  hadi");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_USERNAME_FORMAT);
        Assertions.assertEquals(user.getUsername(), "dibaHadiEsfangereh");

        msg = controller.changeUsername("dibaHadiEsfangereh2");
        Assertions.assertEquals(msg, ProfileMessages.USERNAME_TAKEN);
        Assertions.assertEquals(user.getUsername(), "dibaHadiEsfangereh");

        msg = controller.changeUsername("newUserName");
        Assertions.assertEquals(msg, ProfileMessages.USERNAME_CHANGE_SUCCESS);
        Assertions.assertEquals(user.getUsername(), "newUserName");
    }

    @Test
    public void testChangeNickName() {
        File users = new File(Settings.USERS_PATH);
        users.delete();
        User user = new User("dibaHadiEsfangereh", "Hadie83@",
                "dibahadie@gmail.com", "dibaH", "someSlogan");
        User user2 = new User("dibaHadiEsfangereh2", "Hadie83@",
                "dibahadie2@gmail.com", "dibaH2", "someSlogan");
        Stronghold.load();
        Stronghold.getInstance().addUser(user);
        Stronghold.getInstance().addUser(user2);
        ProfileController controller = new ProfileController(user);

        ProfileMessages msg = controller.changeNickname("newNickName");
        Assertions.assertEquals(msg, ProfileMessages.NICKNAME_CHANGE_SUCCESS);
        Assertions.assertEquals(user.getNickname(), "newNickName");
    }
}
