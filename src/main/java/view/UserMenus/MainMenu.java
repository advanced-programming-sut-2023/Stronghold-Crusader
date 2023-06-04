package view.UserMenus;

import controller.UserControllers.MainController;
import controller.UserControllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User.UserManager;
import view.Main;

public class MainMenu extends Application {
    private static MainController mainController;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stronghold");
        AnchorPane anchorPane = FXMLLoader.load(MainMenu.class.getResource("/FXML/mainMenu.fxml"));
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
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

    public void goToSelectedMapMenu(MouseEvent mouseEvent) {

    }

    public void goToProfileMenu(MouseEvent mouseEvent) throws Exception {
        ProfileMenu.setProfileController(new ProfileController(mainController.currentUser));
        new ProfileMenu().start(Main.mainStage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        UserManager.setLoggedInUser(null);
        new LoginMenu().start(Main.mainStage);
    }
}
