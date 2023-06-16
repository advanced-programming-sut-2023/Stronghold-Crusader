package view.GameMenus;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.enums.AssetType.BuildingType;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicGameMenu extends Application {
    private static Stage stage;
    private static GameController gameController;
    private static GraphicsController graphicsController;
    private static AnchorPane rootPane;
    public ScrollPane mainScrollPane;
    public AnchorPane bottomPane;

    public static AnchorPane getRootPane() {
        return rootPane;
    }

    public static void setGameController(GameController gameController) {
        GraphicGameMenu.gameController = gameController;
    }

    public static void setGraphicsController(GraphicsController graphicsController) {
        GraphicGameMenu.graphicsController = graphicsController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(GraphicGameMenu.class.getResource("/FXML/Gamefxml/gameMenu.fxml"));
        graphicsController.setRootPane(rootPane);
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        GraphicGameMenu.stage = stage;
        stage.show();
    }

    @FXML
    private void initialize() throws IOException {
        mainScrollPane.setContent((graphicsController.getMainGrid()));
        mainScrollPane.setOnKeyPressed(this::handleKeyPressed);
        mainScrollPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) mainScrollPane.setPannable(false);
        });
        mainScrollPane.setOnMouseReleased(mouseEvent -> mainScrollPane.setPannable(true));
        loadDropBuildingMenu();
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case EQUALS:
                graphicsController.zoom(1.2);
                break;
            case MINUS:
                graphicsController.zoom(0.8);
                break;
        }
    }

    private void loadDropBuildingMenu() throws IOException {
        AnchorPane pane = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/DropBuildingfxml/dropBuildingMenu.fxml"));
        bottomPane.getChildren().setAll(pane);
        mainScrollPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainScrollPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        mainScrollPane.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasString()){
                String path = dragboard.getString();
                Pattern pattern = Pattern.compile("/assets/graphic/buildings/(?<name>\\S+)\\.png");
                Matcher matcher = pattern.matcher(path);
                matcher.find();

                Vector2D coordinate = graphicsController.getCoordinate(event, mainScrollPane);
                System.out.println(GraphicBuildingPlacementMenu.controller.dropBuilding(
                        "lookout_tower", coordinate.x, coordinate.y));
            }
            event.consume();
        });
    }
}
