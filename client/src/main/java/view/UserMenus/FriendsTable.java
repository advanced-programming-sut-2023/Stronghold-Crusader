package view.UserMenus;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.User.User;


public class FriendsTable {
    private final ImageView avatar = new ImageView();
    private final String username;
    private final Button accept = new Button("select");

    public FriendsTable(User user) {
        avatar.setImage(new Image(user.getAvatarPath()));
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        this.username = user.getUsername();
        accept.setOnMouseClicked(e -> {
            openPopUp(user);
        });
    }

    private void openPopUp(User user) {
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public Button getAccept() {
        return accept;
    }
}
