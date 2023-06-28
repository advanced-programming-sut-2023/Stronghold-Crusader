package utils;

import controller.UserControllers.FriendsMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import model.Stronghold;
import model.User.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenusUtils {
    public static HashMap<String, String> getInputs(Matcher matcher, String regex) {
        Matcher listOfGroups = Pattern.compile("\\?<(?<groupName>[^>]+)>").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<>();
        while (listOfGroups.find()) {
            groupName = listOfGroups.group("groupName");
            String value = matcher.group(groupName);
            value = removeDoubleQuotation(value);
            inputs.put(groupName, value);
        }
        return inputs;
    }

    public static boolean isNumberValid(String count) {
        try {
            int number = Integer.parseInt(count);
            if (number < 0) return false;
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static String generateRandomPassword() {
        String randomPass = "";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperAlphabet = lowerAlphabet.toUpperCase();
        String nonAlphanumeric = "@#$%^&****@#----@#^^";
        while (randomPass.length() < 6) {
            int character = (int) (Math.random() * 26);
            randomPass += lowerAlphabet.substring(character, character + 1);
            character = (int) (Math.random() * 26);
            randomPass += upperAlphabet.substring(character, character + 1);
            if (randomPass.length() == 6) {
                character %= 18;
                randomPass += nonAlphanumeric.substring(character, character + 1);
                randomPass += Integer.valueOf(character).toString();
            }
        }
        return randomPass;
    }

    public static String generateRandomUsername(String username) {
        String newName = username;
        while (Stronghold.getInstance().userExists(newName)) {
            newName += Integer.valueOf((int) (Math.random() * 9)).toString();
        }
        return newName;
    }

    public static String removeDoubleQuotation(String word) {
        if (word == null) return null;
        if (word.matches("(?=.*[\\s]).+")) {
            return word.substring(1, word.length() - 1);
        }
        return word;
    }

    public static Popup createProfileShowPopUp(User user, boolean friendShip) throws IOException {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        if (friendShip) loadFriendPopUp(user, popup);
        else if (FriendsMenuController.getCurrentUser().isHaveRequestFrom(user))loadSenderPopUp(user, popup);
        else loadUnFriendPopUp(user, popup);
        Stronghold.getInstance().updateData();
        return popup;

    }

    private static void loadUnFriendPopUp(User user, Popup popup) throws IOException {
        AnchorPane pane = FXMLLoader.load(MenusUtils.class.getResource("/FXML/Userfxml/FriendMenu/unFriendUserPopUp.fxml"));
        loadPopUpPaneSetting(user, pane);
        popup.getContent().add(pane);
        pane.getChildren().get(2).setOnMousePressed(e -> {
            popup.hide();
        });
        if (user.isHaveRequestFrom(FriendsMenuController.getCurrentUser())) pane.getChildren().get(3).setVisible(false);

        pane.getChildren().get(3).setOnMouseClicked(e -> {
            user.addSender(FriendsMenuController.getCurrentUser());
            popup.hide();
        });
    }


    private static void loadSenderPopUp(User user, Popup popup) throws IOException {
        AnchorPane pane = FXMLLoader.load(MenusUtils.class.getResource("/FXML/Userfxml/FriendMenu/senderUserPopUp.fxml"));
        loadPopUpPaneSetting(user, pane);
        popup.getContent().add(pane);
        pane.getChildren().get(2).setOnMousePressed(e -> {
            popup.hide();
        });
        pane.getChildren().get(4).setOnMousePressed(e -> {
            user.addFriend(FriendsMenuController.getCurrentUser());
            FriendsMenuController.getCurrentUser().addFriend(user);
            user.removeSender(FriendsMenuController.getCurrentUser());
            FriendsMenuController.getCurrentUser().removeSender(user);
            popup.hide();
        });
        pane.getChildren().get(5).setOnMousePressed(e -> {
            user.removeSender(FriendsMenuController.getCurrentUser());
            FriendsMenuController.getCurrentUser().removeSender(user);
            popup.hide();
        });
    }

    private static void loadFriendPopUp(User user, Popup popup) throws IOException {
        AnchorPane pane = FXMLLoader.load(MenusUtils.class.getResource("/FXML/Userfxml/FriendMenu/friendUserPopUp.fxml"));
        loadPopUpPaneSetting(user, pane);
        popup.getContent().add(pane);
        pane.getChildren().get(2).setOnMousePressed(e -> {
            popup.hide();
        });
        pane.getChildren().get(3).setOnMouseClicked(e -> {
            popup.hide();
            user.removeFriend(FriendsMenuController.getCurrentUser());
            FriendsMenuController.getCurrentUser().removeFriend(user);
        });
    }

    private static void loadPopUpPaneSetting(User user, AnchorPane pane) {
        ((Circle) pane.getChildren().get(0)).setFill(new ImagePattern(new Image(user.getAvatarPath())));
        ((Text) pane.getChildren().get(1)).setText(user.getUsername());
    }


}
