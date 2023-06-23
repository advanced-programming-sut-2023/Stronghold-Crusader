package view.MapMenus;

import controller.MapControllers.MapSelectController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Map.MapManager;
import view.GameMenus.GraphicGameMenu;
import view.UserMenus.ProfileMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GraphicMapSelectMenu extends Application {
    private static MapSelectController mapSelectController;
    public AnchorPane rootPane;
    public ImageView addMapButton, addPlayerButton;
    public ScrollPane mapList;

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
}
