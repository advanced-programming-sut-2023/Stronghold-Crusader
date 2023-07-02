package view.MapMenus.MapCreationMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.GameMenus.GraphicGameMenu;

public class MapCreationMenu extends Application {
    public AnchorPane rootPane;
    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(GraphicGameMenu.class.getResource("/FXML/Mapfxml/MapCreation.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        stage.show();
    }
}
