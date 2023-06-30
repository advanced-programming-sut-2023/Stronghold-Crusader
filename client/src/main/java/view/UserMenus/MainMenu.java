package view.UserMenus;

import controller.ChatControllers.ChatController;
import controller.GameControllers.LobbyController;
import controller.UserControllers.MainController;
import controller.UserControllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Television.Television;
import model.User.UserManager;
import network.Connection;
import network.Request;
import view.ChatMenus.MainChatMenu;
import view.ChatMenus.MainChatMenuController;
import view.GameMenus.Lobby.LobbyMenu;
import view.Main;

public class MainMenu extends Application {
    public static MainController mainController;
    public ImageView newGameButton, profileMenuButton, aboutButton, logoutButton;
    public ImageView chatroomButton;
    public ImageView FriendMenuButton;
    public ImageView ScoreBoardMenuButton;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(MainController.getCurrentUser());
        stage.setTitle("Stronghold");
        AnchorPane anchorPane = FXMLLoader.load(MainMenu.class.getResource("/FXML/Userfxml/mainMenu.fxml"));
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/mainMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }


    public static void setMainController(MainController mainController) {
        MainMenu.mainController = mainController;
    }

    public void goToLobbyMenu() throws Exception {
        LobbyMenu.setLobbyController(new LobbyController());
        new LobbyMenu().start(Main.mainStage);
    }

    public void goToProfileMenu() throws Exception {
        ProfileMenu.setProfileController(new ProfileController(mainController.currentUser));
        new ProfileMenu().start(Main.mainStage);
    }

    public void logout() throws Exception {
        UserManager.setLoggedInUser(null);
        Request request = new Request();
        request.setType("connect");
        request.setCommand("logout");
        request.addParameter("username", mainController.currentUser.getUsername());
        Connection.getInstance().sendRequest(request);
        new LoginMenu().start(Main.mainStage);
    }

    public void goToChatroom() throws Exception {
        ChatController chatController = new ChatController(mainController.currentUser);
        MainChatMenu chatMenu = new MainChatMenu();
        MainChatMenuController.setController(chatController);
        MainChatMenuController.currentChatMenu = chatMenu;
        chatMenu.start(Main.mainStage);
    }

    public void goToScoreBoard() throws Exception {
        ScoreBoardMenu scoreBoardMenu = new ScoreBoardMenu();
        scoreBoardMenu.start(Main.mainStage);
    }

    public void goToTelevision() throws Exception {
        new Television().start(Main.mainStage);
    }
}
