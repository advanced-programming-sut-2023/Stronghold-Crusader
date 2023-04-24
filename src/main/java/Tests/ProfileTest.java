package Tests;

import controller.ProfileController;
import model.Stronghold;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.enums.messages.ProfileMessages;

import java.io.File;

public class ProfileTest {
    private User loadForTest(){
        File users = new File("Resources/users.user");
        users.delete();
        User user = new User("dibaHadiEsfangereh", "Hadie83@",
                "dibahadie@gmail.com", "dibaH", "someSlogan");
        User user2 = new User("dibaHadiEsfangereh2", "Hadie83@",
                "dibahadie2@gmail.com", "dibaH2", "someSlogan");
        Stronghold.load();
        Stronghold.getInstance().addUser(user);
        Stronghold.getInstance().addUser(user2);
        return user;
    }
    @Test
    public void testChangeUsername(){
        User user = loadForTest();
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
    public void testChangeNickName(){
        User user = loadForTest();
        ProfileController controller = new ProfileController(user);

        ProfileMessages msg = controller.changeNickname("newNickName");
        Assertions.assertEquals(msg, ProfileMessages.NICKNAME_CHANGE_SUCCESS);
        Assertions.assertEquals(user.getNickname(), "newNickName");
    }

    @Test
    public void testChangePassword(){
        User user = loadForTest();
        ProfileController controller = new ProfileController(user);
        controller.setCurrentUser(user);

        ProfileMessages msg = controller.changePassword("newPass", "newPass1", "Hadie83@");
        Assertions.assertEquals(msg, ProfileMessages.CONFIRMATION_INCORRECT);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        msg = controller.canChangePassword("Hadie8", "newPass");
        Assertions.assertEquals(msg, ProfileMessages.PASSWORD_INCORRECT);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        msg = controller.canChangePassword("Hadie83@", "newPass");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_PASSWORD_FORMAT);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        msg = controller.canChangePassword("Hadie83@", "N@w2");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_PASSWORD_LENGTH);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        msg = controller.canChangePassword("Hadie83@", "Hadie83@");
        Assertions.assertEquals(msg, ProfileMessages.PASSWORD_NOT_NEW);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        msg = controller.canChangePassword("Hadie83@", "Hadie833@");
        Assertions.assertEquals(msg, ProfileMessages.CAN_CHANGE_PASSWORD);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie83@"));

        user.setPassword("Hadie834@");
        Assertions.assertTrue(user.isPasswordCorrect("Hadie834@"));

        msg = controller.changePassword( "Hadie833@", "Hadie833@", "Hadie83@");
        Assertions.assertEquals(msg, ProfileMessages.PASSWORD_CHANGE_SUCCESS);
        Assertions.assertTrue(user.isPasswordCorrect("Hadie833@"));
    }

    @Test
    public void testChangeEmail(){
        User user = loadForTest();
        ProfileController controller = new ProfileController(user);
        controller.setCurrentUser(user);

        ProfileMessages msg = controller.changeEmail("dibahadie2@gmail.com");
        Assertions.assertEquals(msg, ProfileMessages.EMAIL_EXISTS);
        Assertions.assertEquals(user.getEmail(), "dibahadie@gmail.com");

        msg = controller.changeEmail("email");
        Assertions.assertEquals(msg, ProfileMessages.INVALID_EMAIL_FORMAT);
        Assertions.assertEquals(user.getEmail(), "dibahadie@gmail.com");

        msg = controller.changeEmail("dibahadi@gmail.com");
        Assertions.assertEquals(msg, ProfileMessages.EMAIL_CHANGE_SUCCESS);
        Assertions.assertEquals(user.getEmail(), "dibahadi@gmail.com");
    }
}
