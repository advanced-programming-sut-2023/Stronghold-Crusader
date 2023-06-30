package view.MapMenus.MapCreationMenu;

import controller.GameControllers.GraphicsController;
import controller.MapControllers.MapCreationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import model.Map.Map;
import view.GameMenus.GraphicGameMenu;

import java.io.IOException;

public class MapCreationGraphicController {
    public ScrollPane mainScrollPane;
    public AnchorPane bottomPane;
    public AnchorPane dropBuildingMenu, cellTypeMenu;
    public AnchorPane rootPane;
    private Map map;
    private static MapCreationController controller;
    private static GraphicsController graphicsController;

    public static void setController(MapCreationController controller) {
        MapCreationGraphicController.controller = controller;
    }

    public static void setGraphicsController(GraphicsController graphicsController) {
        MapCreationGraphicController.graphicsController = graphicsController;
        graphicsController.setMapCreation(true);
    }

    public void initialize() throws IOException {
        graphicsController.loadGraphics();
        mainScrollPane.setContent(graphicsController.getMainGrid());
        initializeDropBuildingMenu();
    }

    public void initializeDropBuildingMenu() throws IOException {
        dropBuildingMenu = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/DropBuildingfxml/dropBuildingMenu.fxml"));
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(dropBuildingMenu);
        mainScrollPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainScrollPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
    }

    public void loadCellTypeMenu() throws IOException {
        cellTypeMenu = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Mapfxml/changeCellType.fxml"));
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(cellTypeMenu);
        mainScrollPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainScrollPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
    }
}
