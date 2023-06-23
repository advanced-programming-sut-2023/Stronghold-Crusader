package view.MapMenus;

import controller.GameControllers.GraphicsController;
import controller.MapControllers.MapSelectController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Game.Game;
import model.Map.MapManager;
import model.Stronghold;
import view.GameMenus.GraphicGameMenu;
import view.UserMenus.ProfileMenu;
import view.enums.messages.MapMessage.MapSelectMessage;


import java.util.ArrayList;

public class GraphicMapSelectMenu extends Application {
    private static MapSelectController mapSelectController;
    public AnchorPane rootPane;
    public ImageView addMapButton, addPlayerButton;
    public ScrollPane mapList, mapPreview;
    public TextField mapNameField, colorField, playerNameField;
    public VBox selectedPlayers;

    public static void setMapSelectController(MapSelectController mapSelectController) {
        GraphicMapSelectMenu.mapSelectController = mapSelectController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(GraphicGameMenu.class.getResource("/FXML/Mapfxml/mapSelectMenu2.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/mapSelectMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.show();
    }

    public void initialize(){
        initializeMapList();
        mapPreview.getStylesheets().add(
                GraphicGameMenu.class.getResource("/CSS/MapCss/mapSelectStyle.css").toExternalForm());
        mapPreview.setOpacity(0.5);
        addMapButton.setOnMouseClicked(e -> selectMap());
        addPlayerButton.setOnMouseClicked(e -> addPlayer());
        Stronghold.load();
        mapSelectController = new MapSelectController(Stronghold.getInstance().getUser("diba"));
    }

    private void initializeMapList(){
        ArrayList<ArrayList<String>> maps = MapManager.getMapList();
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        int i = 1;
        for (ArrayList<String> map : maps) {
            String output = (i++) + ". Map id: " + map.get(0) + ", Map name: " + map.get(1) + ", Number of players: "
                    + map.get(2) + "\n";
            Label label = new Label(output);
            label.setStyle("-fx-text-fill: white");
            vBox.getChildren().add(label);
        }
        mapList.setContent(vBox);
    }

    public void selectMap(){
        String mapId = mapNameField.getText();
        mapNameField.clear();
        MapSelectMessage msg = mapSelectController.selectMap(mapId, false);
        if (msg.equals(MapSelectMessage.MAP_SELECT_SUCCESS)) {
            GraphicsController graphicsController = new GraphicsController(
                    new Game(mapSelectController.getSelectedMap(), mapSelectController.getPlayers(), false));
            graphicsController.loadGraphics();
            mapPreview.setContent(graphicsController.getMainGrid());
            mapPreview.setOpacity(1);
        } else {
            // TODO : Handle errors
        }
    }

    public void addPlayer(){
        String username = playerNameField.getText();
        String colorName = colorField.getText();
        playerNameField.clear();
        colorField.clear();
        MapSelectMessage msg = mapSelectController.addPlayer(username, colorName);
        if (msg.equals(MapSelectMessage.PLAYER_ADD_SUCCESS)) {
            Label label = new Label(" " + username + " ,color : " + colorName);
            label.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
            selectedPlayers.getChildren().add(label);
        } else {
            // TODO : handle errors
        }
    }
}
