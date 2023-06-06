package view.GameMenus;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphicGameMenu extends Application {
    private static Stage stage;
    private static GameController gameController;
    private static GraphicsController graphicsController;
    private static AnchorPane rootPane;
    public ScrollPane mainScrollPane;

    public static void setGameController(GameController gameController) {
        GraphicGameMenu.gameController = gameController;
    }

    public static void setGraphicsController(GraphicsController graphicsController) {
        GraphicGameMenu.graphicsController = graphicsController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(GraphicGameMenu.class.getResource("/FXML/gameMenu.fxml"));
        graphicsController.setRootPane(rootPane);
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        GraphicGameMenu.stage = stage;
        stage.show();
    }

    @FXML
    private void initialize() {
        mainScrollPane.setContent((graphicsController.getMainGrid()));
        mainScrollPane.setOnKeyPressed(this::handleKeyPressed);
        mainScrollPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) mainScrollPane.setPannable(false);
        });
        mainScrollPane.setOnMouseReleased(mouseEvent -> mainScrollPane.setPannable(true));
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
}
