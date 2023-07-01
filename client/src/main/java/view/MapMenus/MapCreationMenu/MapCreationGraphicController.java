package view.MapMenus.MapCreationMenu;

import controller.GameControllers.GraphicsController;
import controller.MapControllers.MapCreationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Map.Map;
import model.Map.MapManager;
import view.GameMenus.GraphicGameMenu;
import view.Main;
import view.UserMenus.MainMenu;

import java.io.IOException;

public class MapCreationGraphicController {
    public ScrollPane mainScrollPane;
    public AnchorPane bottomPane;
    public AnchorPane dropBuildingMenu, cellTypeMenu;
    public AnchorPane rootPane;
    public VBox rightPane;
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
        map = controller.getMap();
        graphicsController.loadGraphics();
        mainScrollPane.setContent(graphicsController.getMainGrid());
        initializeDropBuildingMenu();
        updateRightPane();
    }

    public void initializeDropBuildingMenu() throws IOException {
        dropBuildingMenu = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/DropBuildingfxml/dropBuildingMenu.fxml"));
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(dropBuildingMenu);
        mainScrollPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainScrollPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
                try {
                    updateRightPane();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    public void updateRightPane() throws IOException {
        int num = map.getPlayerCount();
        rightPane.getChildren().clear();
        for (int i = 0; i < num; i++) {
            AnchorPane anchorPane = FXMLLoader.load(GraphicGameMenu.class.
                    getResource("/FXML/Mapfxml/Governance.fxml"));
            if (map.getStoreHouseCoordinate(i) != null) anchorPane.getChildren().get(1).setOpacity(1);
            if (map.getHeadQuarterCoordinate(i) != null) anchorPane.getChildren().get(0).setOpacity(1);
            rightPane.getChildren().add(anchorPane);
        }
    }

    public void createMap() throws Exception {
        if (map.getHeadQuarters().size() < map.getPlayerCount()) return;
        if (map.getStoreHouses().size() < map.getPlayerCount()) return;
        MapManager.createSavableMap(map);
        MainMenu.mainController.menu.start(Main.mainStage);
    }
}
