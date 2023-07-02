package view.UserMenus;

import controller.UserControllers.MainController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Stronghold;
import model.User.User;
import utils.MenusUtils;
import view.Main;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ScoreBoardTable {
    private final int rank;
    private final Circle avatar = new Circle();
    private final String username;
    private final int highScore;
    private final ImageView connectionMode = new ImageView();
    private final ImageView television = new ImageView();
    private final Button select = new Button("select");
    private String lastSeen;

    public ScoreBoardTable(User user) {
        rank = user.getRank();
        username = user.getUsername();
        highScore = user.getHighScore();
        setDetailsForImages(user);
        select.setOnMouseClicked(e -> {
            try {
                MenusUtils.createProfileShowPopUp
                        (user, MainController.getCurrentUser().isFriend(user)).show(Main.mainStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        if (!Stronghold.getInstance().isUserOnline(user.getUsername()))
            createLastSeen(user.getLastOnlineTime());
        else lastSeen = "now";
    }

    private void createLastSeen(long lastOnlineTime) {
        if (lastOnlineTime == 0) lastSeen = "long time ago";
        else {
            long time = ((System.currentTimeMillis() - lastOnlineTime));
            lastSeen = Integer.valueOf((int) TimeUnit.MILLISECONDS.toMinutes(time)).toString()
                    + " minutes ago";
        }
    }


    private void setDetailsForImages(User user) {
        avatar.setRadius(24);
        connectionMode.setFitHeight(35);
        connectionMode.setFitWidth(35);
        television.setFitWidth(35);
        television.setFitHeight(35);
        if (Stronghold.getInstance().isUserOnline(user.getUsername()))
            connectionMode.setImage(new Image(ScoreBoardTable.class.getResource("/assets/icons/green.png").toString()));
        else connectionMode.setImage(new Image(ScoreBoardTable.class.getResource("/assets/icons/red.png").toString()));
        avatar.setFill(new ImagePattern(new Image(user.getAvatarPath())));
        television.setImage(new Image(
                ScoreBoardTable.class.getResource("/assets/icons/television.png").toString()));


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

    public String getLastSeen() {
        return lastSeen;
    }
}
