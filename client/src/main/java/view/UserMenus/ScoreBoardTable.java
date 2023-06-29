package view.UserMenus;

import controller.UserControllers.FriendsMenuController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Stronghold;
import model.User.User;
import utils.MenusUtils;

import java.io.IOException;

public class ScoreBoardTable {
    private final int rank;
    private final Circle avatar = new Circle();
    private final String username;
    private final int highScore;
    private final ImageView connectionMode = new ImageView();
    private final ImageView television = new ImageView();
    private final Button select = new Button("select");

    public ScoreBoardTable(User user) {
        rank = user.getRank();
        username = user.getUsername();
        highScore = user.getHighScore();
        setDetailsForImages(user);
        select.setOnMouseClicked(e -> {
            try {
                MenusUtils.createProfileShowPopUp
                        (user, FriendsMenuController.getCurrentUser().isFriend(user)).show(FriendsMenu.getStage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void setDetailsForImages(User user) {
        avatar.setRadius(24);
        connectionMode.setFitHeight(35);
        connectionMode.setFitWidth(35);
        television.setFitWidth(35);
        television.setFitHeight(35);
        if (Stronghold.getInstance().isUserOnline(user.getUsername()))
            connectionMode.setImage(new Image(ScoreBoardTable.class.getResource("assets/icons/green.png").toExternalForm()));
        else connectionMode.setImage(new Image(ScoreBoardTable.class.getResource("assets/icons/red.png").toExternalForm()));
        avatar.setFill(new ImagePattern(new Image(user.getAvatarPath())));
        television.setImage(new Image(ScoreBoardTable.class.getResource("assets/icons/television.png").toExternalForm()));
    }

    public int getRank() {
        return rank;
    }

    public Circle getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public ImageView getConnectionMode() {
        return connectionMode;
    }

    public ImageView getTelevision() {
        return television;
    }

    public Button getSelect() {
        return select;
    }
}
