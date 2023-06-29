package view.ChatMenus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.chatRoom.Chat;
import view.Main;
import view.UserMenus.LoginMenu;
import view.UserMenus.MainMenu;

import java.net.URL;

public class ChatCreationMenu extends Application {
    public ImageView privateButton;
    public ImageView groupButton;
    private Chat.ChatMode chatType;
    private static MainChatMenu chatMenu;

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
     public void selectPrivateChat(){
        chatType = Chat.ChatMode.PRIVATE;
        privateButton.setOpacity(1);
        groupButton.setOpacity(0.5);
     }

     public void selectGroupChat(){
        chatType = Chat.ChatMode.ROOM;
        groupButton.setOpacity(1);
        privateButton.setOpacity(0.5);
     }

     public void back() throws Exception {
         chatMenu.start(Main.mainStage);
     }
}
