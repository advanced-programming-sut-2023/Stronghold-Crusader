package view.UserMenus;

import controller.UserControllers.FriendsMenuController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User.User;
import utils.MenusUtils;

import java.io.IOException;


public class FriendsTable {
    private final Circle avatar = new Circle();
    private final String username;
    private final Button accept = new Button("select");

    public FriendsTable(User user) {
        avatar.setFill(new ImagePattern(new Image(user.getAvatarPath())));
        avatar.setRadius(24);
        this.username = user.getUsername();
        accept.setOnMouseClicked(e -> {
            try {
                MenusUtils.createProfileShowPopUp
                        (user, FriendsMenuController.getCurrentUser().isFriend(user)).show(FriendsMenu.getStage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    public Circle getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public Button getAccept() {
        return accept;
    }
}
