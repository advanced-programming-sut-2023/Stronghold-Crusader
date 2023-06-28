package view.ChatMenus;

import controller.ChatControllers.ChatController;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainChatMenu extends Application {

    public TextField text;
    public ImageView sendButton;
    public VBox chatList;
    public VBox chatPane;
    public ScrollPane chatPaneScroll;
    public ScrollPane chatListScroll;
    private static ChatController controller;

    public static void setController(ChatController controller) {
        MainChatMenu.controller = controller;
    }

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
        loadChat(ChatManager.loadGlobalChat());
    }

    public void processSendMessage() throws IOException {
        String content = text.getText();
        text.clear();
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Message msg = new Message("diba", content,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        sendMessage(msg);
        controller.addMessage(msg);
    }

    public void processReceiveMessage() throws IOException {
        String content = text.getText();
        text.clear();
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Message msg = new Message("diba", content,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        sendMessage(msg);
        controller.addMessage(msg);
    }



    public void loadChat(Chat chat) throws IOException {
        if (controller.getCurrentChat() != null) controller.updateChat();
        controller.setCurrentChat(chat);
        if (chat == null) chatPane = new VBox();
        else {
            String userName = chat.getOwner();
            chatPane = new VBox();
            chatPane.setAlignment(Pos.CENTER);
            chatPane.setPrefWidth(549);
            for (Message msg : chat.getMessages()) {
                addMessage(msg, userName);
            }
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

    public void loadPrivateChat() throws IOException {
        chatList = new VBox();
        chatList.setPrefWidth(161);
        chatList.setAlignment(Pos.CENTER);
        ArrayList<Chat> chats = ChatManager.loadPrivateChats();
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
            sendMessage(msg);
        } else {
            // if message is unseen set it to seen and save it in the controller
            receiveMessage(msg);
        }
    }

    public void sendMessage(Message msg) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                getResource("/FXML/Chatfxml/CurrentUserMessagefxml.fxml").toExternalForm()));
        ((Label) anchorPane.getChildren().get(2)).setText(msg.getText());
        String time = msg.getHour() + ":" + msg.getMinute();
        ((Label) anchorPane.getChildren().get(3)).setText(time);
        chatPane.getChildren().add(anchorPane);
    }

    public void receiveMessage(Message msg) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                getResource("/FXML/Chatfxml/OtherUserMessagefxml.fxml").toExternalForm()));
        ((Label) anchorPane.getChildren().get(2)).setText(msg.getText());
        String time = msg.getHour() + ":" + msg.getMinute();
        ((Label) anchorPane.getChildren().get(3)).setText(time);
        chatPane.getChildren().add(anchorPane);
    }

    public void loadGlobalChat(){
        chatListScroll.setContent(null);
        try {
            loadChat(ChatManager.loadGlobalChat());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
