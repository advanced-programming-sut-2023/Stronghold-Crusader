package view.ChatMenus;

import controller.ChatControllers.ChatController;
import controller.ChatControllers.ChatCreationController;
import controller.ChatControllers.ReactionManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Stronghold;
import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import model.chatRoom.Message;
import view.GameMenus.MarketMenus.MarketMenu;
import view.Main;
import view.UserMenus.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainChatMenuController {
    public TextField text;
    public ImageView sendButton;
    public VBox chatList;
    public VBox chatPane;
    public ScrollPane chatPaneScroll;
    public ScrollPane chatListScroll;
    private static ChatController controller;
    public static MainChatMenu currentChatMenu;
    public ImageView updateButton;

    public static void setController(ChatController controller) {
        MainChatMenuController.controller = controller;
    }

    public static ChatController getController() {
        return controller;
    }
    private void sendWithEnterHandler(Stage stage) {
        if (stage.getScene().getOnKeyPressed() != null) return;
        stage.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    processSendMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void initialize() throws IOException {
        ChatController.currentMenu = this;
        loadChat(controller.getGlobalChat());
        updateButton.setVisible(false);
        updateButton.setManaged(false);
    }

    public void processSendMessage() throws IOException {
        String content = text.getText();
        text.clear();
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Message msg = new Message(controller.getCurrentUsername(), content,
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
        controller.setCurrentChat(chat);
        if (chat == null) chatPane = new VBox();
        else {
            chatPane = new VBox();
            chatPane.setAlignment(Pos.CENTER);
            chatPane.setPrefWidth(549);
            for (Message msg : chat.getMessages()) {
                if (msg.isVisibleForUser(controller.getCurrentUsername())) continue;
                addMessage(msg);
            }
        }
        chatPaneScroll.setContent(chatPane);
    }

    public void loadRoomChats() throws IOException {
        chatList = new VBox();
        chatList.setPrefWidth(161);
        chatList.setAlignment(Pos.CENTER);
        ArrayList<Chat> chats = controller.loadRoomChats();
        for (Chat chat : chats) {
            if (chat.getUsers().contains(controller.getCurrentUsername()))
                addChatItem(chat);
        }
        chatListScroll.setContent(chatList);
    }

    public void loadPrivateChat() throws IOException {
        chatList = new VBox();
        chatList.setPrefWidth(161);
        chatList.setAlignment(Pos.CENTER);
        ArrayList<Chat> chats = controller.loadPrivateChats();
        for (Chat chat : chats) {
            if (chat.getUsers().contains(controller.getCurrentUsername()))
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

    public void addMessage(Message msg) throws IOException {
        if (msg.getSenderUsername().equals(controller.getCurrentUsername())) {
            sendMessage(msg);
        } else {
            receiveMessage(msg);
        }
    }

    public void sendMessage(Message msg) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                getResource("/FXML/Chatfxml/CurrentUserMessagefxml.fxml").toExternalForm()));
        processMessageBox(msg, anchorPane);
    }

    public void receiveMessage(Message msg) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(new URL(MarketMenu.class.
                getResource("/FXML/Chatfxml/OtherUserMessagefxml.fxml").toExternalForm()));
        processMessageBox(msg, anchorPane);
    }

    private void processMessageBox(Message msg, AnchorPane anchorPane) {
        ((Label) anchorPane.getChildren().get(2)).setText(msg.getText());
        String time = msg.getHour() + ":" + msg.getMinute();
        ((Label) anchorPane.getChildren().get(3)).setText(time);
        ReactionManager.setReactions(msg, anchorPane);
        ReactionManager.setReactionHandling(msg, anchorPane);
        ReactionManager.setDelete(anchorPane, msg);
        ReactionManager.setTwoWayDelete(anchorPane, msg, controller.getCurrentChat());
        ReactionManager.setEdit(anchorPane, msg);
        ReactionManager.setSeenStatus(msg, anchorPane);
        ReactionManager.setUserName(msg, anchorPane);
        chatPane.getChildren().add(anchorPane);
    }

    public void loadGlobalChat() {
        chatListScroll.setContent(null);
        try {
            loadChat(controller.getGlobalChat());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToChatCreation() throws Exception {
        ChatCreationMenu chatCreationMenu = new ChatCreationMenu();
        ChatCreationMenu.setChatMenu(currentChatMenu);
        ChatCreationMenu.setController(
                new ChatCreationController(Stronghold.getInstance().getUser(controller.getCurrentUsername())));
        chatCreationMenu.start(Main.mainStage);
        ChatController.currentMenu = null;
    }

    public void back() throws Exception {
        MainMenu.mainController.menu.start(Main.mainStage);
        ChatController.currentMenu = null;
    }

    public void EnterHandler(MouseEvent mouseEvent) {
        sendWithEnterHandler(MainChatMenu.getMainStage());
    }

    public void showEditingContent(Message msg){
        text.setText(msg.getText());
        updateButton.setVisible(true);
        updateButton.setManaged(true);
        updateButton.setOnMouseClicked(e -> {
            msg.setText(text.getText());
            ChatManager.updateChat(controller.getCurrentChat());
            updateButton.setVisible(false);
            updateButton.setManaged(false);
            text.clear();
        });
    }
}
