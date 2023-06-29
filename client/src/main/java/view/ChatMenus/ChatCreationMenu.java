package view.ChatMenus;

import controller.ChatControllers.ChatCreationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Stronghold;
import model.User.User;
import model.chatRoom.Chat;
import view.Main;
import view.UserMenus.LoginMenu;
import view.UserMenus.MainMenu;
import view.enums.messages.ChatMessage.ChatMessage;

import java.net.URL;

public class ChatCreationMenu extends Application {
    public ImageView privateButton;
    public ImageView groupButton;
    public TextField participantField;
    public TextField chatIdField;
    public ScrollPane participantList;
    public Label errorMessageText;
    private VBox vBox;
    private static MainChatMenu chatMenu;
    private static ChatCreationController controller;

    public static void setController(ChatCreationController controller) {
        ChatCreationMenu.controller = controller;
    }

    public static void setChatMenu(MainChatMenu chatMenu) {
        ChatCreationMenu.chatMenu = chatMenu;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/Chatfxml/CreateChat.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/ChatCreation.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void initialize() {
        vBox = new VBox();
        vBox.setSpacing(10);
        participantList.setContent(vBox);
    }

    public void selectPrivateChat() {
        privateButton.setOpacity(1);
        groupButton.setOpacity(0.5);
        controller.setMode(Chat.ChatMode.PRIVATE);
    }

    public void selectGroupChat() {
        groupButton.setOpacity(1);
        privateButton.setOpacity(0.5);
        controller.setMode(Chat.ChatMode.ROOM);
    }

    public void back() throws Exception {
        chatMenu.start(Main.mainStage);
    }

    public void addParticipant() {
        String username = participantField.getText();
        ChatMessage msg = controller.addUser(username);
        if (!msg.equals(ChatMessage.USER_ADDED_SUCCESSFULLY)) printError(msg.getMessage());
        else {
            User user = Stronghold.getInstance().getUser(username);
            String output = " " + username + " nickname : " + user.getNickname();
            Label label = new Label(output);
            label.setStyle("-fx-text-fill: white");
            vBox.getChildren().add(label);
        }
    }

    public void createChat() throws Exception {
        ChatMessage msg = controller.CreateChat(chatIdField.getText());
        if (!msg.equals(ChatMessage.CHAT_CREATED_SUCCESSFULLY)) printError(msg.getMessage());
        else {
            back();
        }
    }

    public void printError(String text) {
        Platform.runLater(() -> {
            errorMessageText.setText(text);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> errorMessageText.setText(""))
            );
            timeline.setCycleCount(1);
            timeline.play();
        });
    }
}
