package view.MapMenus.MapCreationMenu;

import controller.MapControllers.MapCreationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.GameMenus.GraphicGameMenu;
import view.Main;
import view.MapMenus.MapManagerMenu;
import view.UserMenus.MainMenu;
import view.UserMenus.ProfileMenu;
import view.enums.messages.MapMessage.MapCreationMessage;

public class MapCreationInitial extends Application {
    public AnchorPane rootPane;
    public Label errorMessageText;
    public static MapCreationController controller;
    public TextField mapIdField;
    public TextField mapX;
    public TextField PlayerNumberField;
    public TextField mapY;

    public static void setMapCreationController(MapCreationController mapCreationController) {
        MapCreationInitial.controller = mapCreationController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(GraphicGameMenu.class.getResource("/FXML/Mapfxml/MapCreationInitial.fxml"));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/mapSelectMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        stage.show();
    }

    public void back() throws Exception {
        MainMenu.mainController.menu.start(Main.mainStage);
    }

    public void createMap() throws Exception {
        int mapXNum, mapYNum, playerNum;
        try {
            mapXNum = Integer.parseInt(mapX.getText());
            mapYNum = Integer.parseInt(mapY.getText());
        } catch (NumberFormatException e){
            mapXNum = -1;
            mapYNum = -1;
        }
        try {
            playerNum = Integer.parseInt(PlayerNumberField.getText());
        } catch (NumberFormatException e) {
            playerNum = -1;
        }
        MapCreationMessage msg = controller.initializeMap(mapIdField.getText(), mapYNum, mapXNum, playerNum);
        if (msg != MapCreationMessage.MAP_INITIALIZE_SUCCESS) printError(msg.getMessage());
        else {
            controller.goToMapCreationMenu();
        }
    }

    public void printError(String text) {
        errorMessageText.setText(text);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> errorMessageText.setText(""))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void goToMapManager() throws Exception {
        MapManagerMenu menu = new MapManagerMenu();
        menu.start(Main.mainStage);
    }
}
