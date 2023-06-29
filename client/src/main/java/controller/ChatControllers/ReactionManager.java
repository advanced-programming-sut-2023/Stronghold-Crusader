package controller.ChatControllers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.chatRoom.Message;
import view.UserMenus.MainMenu;

import java.util.HashMap;

public class ReactionManager {
    public static void setReactions(Message msg, AnchorPane anchorPane) {
        int likeCount = 0, heartCount = 0, dislikeCount = 0, fireCount = 0;
        HashMap<String, Message.Reaction> reactions = msg.getReactions();
        for (String person : reactions.keySet()) {
            switch (reactions.get(person)) {
                case FIRE:
                    fireCount++;
                    break;
                case LIKE:
                    likeCount++;
                    break;
                case HEART:
                    heartCount++;
                    break;
                case DISLIKE:
                    dislikeCount++;
                    break;
            }
        }
        ((Label) anchorPane.getChildren().get(8)).setText(Integer.toString(dislikeCount));
        ((Label) anchorPane.getChildren().get(9)).setText(Integer.toString(fireCount));
        ((Label) anchorPane.getChildren().get(10)).setText(Integer.toString(heartCount));
        ((Label) anchorPane.getChildren().get(11)).setText(Integer.toString(likeCount));
    }

    public static void setReactionHandling(Message msg, AnchorPane anchorPane) {
        (anchorPane.getChildren().get(8)).setOnMouseClicked(e -> dislike(anchorPane, msg));
        (anchorPane.getChildren().get(9)).setOnMouseClicked(e -> fire(anchorPane, msg));
        (anchorPane.getChildren().get(10)).setOnMouseClicked(e -> heart(anchorPane, msg));
        (anchorPane.getChildren().get(11)).setOnMouseClicked(e -> like(anchorPane, msg));
    }

    public static void like(AnchorPane anchorPane, Message msg) {
        String username = MainMenu.mainController.currentUser.getUsername();
        if (userNotReacted(username, msg)) {
            String text = ((Label) anchorPane.getChildren().get(11)).getText();
            int num = Integer.parseInt(text);
            num++;
            ((Label) anchorPane.getChildren().get(11)).setText(Integer.toString(num));
            msg.addReaction(username, Message.Reaction.LIKE);
        }
    }

    public static void dislike(AnchorPane anchorPane, Message msg) {
        String username = MainMenu.mainController.currentUser.getUsername();
        if (userNotReacted(username, msg)) {
            String text = ((Label) anchorPane.getChildren().get(8)).getText();
            int num = Integer.parseInt(text);
            num++;
            ((Label) anchorPane.getChildren().get(8)).setText(Integer.toString(num));
            msg.addReaction(MainMenu.mainController.currentUser.getUsername(), Message.Reaction.DISLIKE);
        }
    }

    public static void heart(AnchorPane anchorPane, Message msg) {
        String username = MainMenu.mainController.currentUser.getUsername();
        if (userNotReacted(username, msg)) {
            String text = ((Label) anchorPane.getChildren().get(10)).getText();
            int num = Integer.parseInt(text);
            num++;
            ((Label) anchorPane.getChildren().get(10)).setText(Integer.toString(num));
            msg.addReaction(MainMenu.mainController.currentUser.getUsername(), Message.Reaction.HEART);
        }
    }

    public static void fire(AnchorPane anchorPane, Message msg) {
        String username = MainMenu.mainController.currentUser.getUsername();
        if (userNotReacted(username, msg)) {
            String text = ((Label) anchorPane.getChildren().get(9)).getText();
            int num = Integer.parseInt(text);
            num++;
            ((Label) anchorPane.getChildren().get(9)).setText(Integer.toString(num));
            msg.addReaction(MainMenu.mainController.currentUser.getUsername(), Message.Reaction.FIRE);
        }
    }

    public static boolean userNotReacted(String username, Message msg) {
        return !msg.getReactions().containsKey(username);
    }
}
