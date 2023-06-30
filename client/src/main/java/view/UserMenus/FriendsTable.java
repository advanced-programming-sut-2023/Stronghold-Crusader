package view.UserMenus;

import controller.UserControllers.MainController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User.User;
import utils.MenusUtils;

import java.io.IOException;


public class FriendsTable {
    private final Circle avatar = new Circle();
    private final String username;
    private final Button accept = new Button("select");
    private final ImageView isFriend = new ImageView();
    private final String email;

    public FriendsTable(User user) {
        email = user.getEmail();
        setIsFriendImage(MainController.getCurrentUser().isFriend(user));
        avatar.setFill(new ImagePattern(new Image(user.getAvatarPath())));
        avatar.setRadius(24);
        isFriend.setFitHeight(35);
        isFriend.setFitWidth(35);
        this.username = user.getUsername();
        accept.setOnMouseClicked(e -> {
            try {
                MenusUtils.createProfileShowPopUp
                        (user, MainController.getCurrentUser().isFriend(user)).show(FriendsMenu.getStage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setIsFriendImage(boolean isFriend) {
        if (isFriend)
            this.isFriend.setImage(new Image(FriendsTable.class.getResource("/assets/icons/accepted.png").toString()));
        else this.isFriend.setImage(new Image(FriendsTable.class.getResource("/assets/icons/deny.png").toString()));
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

    public ImageView getIsFriend() {
        return isFriend;
    }

    public String getEmail() {
        return email;
    }
}
