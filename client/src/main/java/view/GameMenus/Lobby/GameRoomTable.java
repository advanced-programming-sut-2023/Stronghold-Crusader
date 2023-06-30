package view.GameMenus.Lobby;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.User.User;

public class GameRoomTable {
    private final Circle avatar = new Circle();
    private  String nickname;
    private final Circle color = new Circle();

    public GameRoomTable(User user, boolean isAdmin, String color) {
        this.nickname = user.getUsername();
        if (isAdmin) nickname += "(Admin)";
        avatar.setRadius(25);
        this.color.setRadius(10);
        this.color.setFill(Paint.valueOf(color));
        avatar.setFill(new ImagePattern(new Image(user.getAvatarPath())));
    }

    public Circle getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public Circle getColor() {
        return color;
    }
}
