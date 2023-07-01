package view.MapMenus;

import controller.UserControllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.Main;
import view.UserMenus.LoginMenu;
import view.UserMenus.MainMenu;

import java.net.URL;
import java.util.ArrayList;

public class MapManagerMenu extends Application {

    private static Stage mainStage;
    public ScrollPane mapListScroll;
    public ScrollPane receivedRequestScroll;
    public TextField usernameField;
    public TextField mapNameField;
    public Label errorMessageText;

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/Mapfxml/mapManager.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/mapManager.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        mainStage = stage;
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public void initialize(){
        loadMaps();
    }

    public void loadMaps(){
        ArrayList<String> maps = MainController.currentUser.getMapList();
        VBox vBox = new VBox();
        int i=1;
        for (String map : maps) {
            String output = (i++) + ". Map id: " + map;
            Label label = new Label(output);
            label.setStyle("-fx-text-fill: white");
            vBox.getChildren().add(label);
        }
        mapListScroll.setContent(vBox);
    }

    public void loadRequests(){

    }
    public void back() throws Exception {
        MainMenu.mainController.menu.start(Main.mainStage);
    }
}
