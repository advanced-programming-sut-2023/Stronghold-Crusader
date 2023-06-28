package view.ChatMenus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import model.chatRoom.Message;
import view.GameMenus.MarketMenus.MarketMenu;
import view.UserMenus.LoginMenu;
import view.UserMenus.MainMenu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainChatMenu extends Application {

    public TextField text;
    public ImageView sendButton;
    public VBox chatList;
    public VBox chatPane;
    public ScrollPane chatPaneScroll;
    public ScrollPane chatListScroll;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/Chatfxml/MainChatFxml.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/chatRoomMenu.PNG").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }


    public void initialize() throws IOException {
        Chat chat = ChatManager.loadChat("chat1", Chat.ChatMode.PRIVATE);
        loadChat(chat);
    }

    public void loadChat(Chat chat) throws IOException {
        String userName = chat.getOwner();
        chatPane = new VBox();
        chatPane.setAlignment(Pos.CENTER);
        chatPane.setPrefWidth(549);
        for (Message msg : chat.getMessages()) {
            addMessage(msg, userName);
        }
        chatPaneScroll.setContent(chatPane);
    }

    public void loadRoomChats() throws IOException {
        chatList = new VBox();
        chatList.setPrefWidth(161);
        chatList.setAlignment(Pos.CENTER);
        ArrayList<Chat> chats = ChatManager.loadRoomChats();
        for (Chat chat : chats) {
            addChatItem(chat);
        }
        chatListScroll.setContent(chatList);
    }

    public void addChatItem(Chat chat) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                getResource("/FXML/Chatfxml/ChatIcon.fxml").toExternalForm()));
        ((Label) anchorPane.getChildren().get(1)).setText(chat.getChatId());
        anchorPane.setOnMouseClicked(e -> {
            try {
                loadChat(chat);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        chatList.getChildren().add(anchorPane);
    }

    public void addMessage(Message msg, String userName) throws IOException {
        if (msg.getSenderUsername().equals(userName)) {
            AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                    getResource("/FXML/Chatfxml/CurrentUserMessagefxml.fxml").toExternalForm()));
            ((Label) anchorPane.getChildren().get(2)).setText(msg.getText());
            String time = msg.getHour() + ":" + msg.getMinute();
            ((Label) anchorPane.getChildren().get(3)).setText(time);
            chatPane.getChildren().add(anchorPane);
        } else {
            AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                    getResource("/FXML/Chatfxml/OtherUserMessagefxml.fxml").toExternalForm()));
            ((Label) anchorPane.getChildren().get(2)).setText(msg.getText());
            String time = msg.getHour() + ":" + msg.getMinute();
            ((Label) anchorPane.getChildren().get(3)).setText(time);
            chatPane.getChildren().add(anchorPane);
        }
    }

    private void createChatBox(Message msg) {

    }

    public void loadPrivateChats() {

    }

    public void loadChatRooms() {

    }
}
