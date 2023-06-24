package view.GameMenus;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicGameMenu extends Application {
    private static Stage stage;
    private static GameController gameController;
    private static GraphicsController graphicsController;
    private static AnchorPane rootPane;
    public ScrollPane mainScrollPane;
    public AnchorPane bottomPane;
    public AnchorPane leftPane;
    public Label popularityLabel;
    public VBox selectedUnitsMenu;
    public Slider fearRateSlider;
    public Slider foodRateSlider;
    public Slider taxRateSlider;
    public Label goldLabel;
    public Label populationLabel;
    public ImageView closeLeftBarBtn;
    public ImageView openLeftBarBtn;
    public Label roundLabel;
    public Label playerLabel;
    public AnchorPane dropBuildingMenu, marketMenu;

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
        initializeLeftPane();
        loadDropBuildingMenu();
        loadMarket();
        updateMenuValues();
    }

    private void initializeLeftPane() {
        leftPane.setBackground(new Background(new BackgroundImage(new Image(
                GraphicGameMenu.class.getResource("/assets/graphic/overlay/left_menu.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        closeLeftBarBtn.setOnMouseClicked(mouseEvent -> {
            leftPane.setVisible(false);
            openLeftBarBtn.setVisible(true);
            closeLeftBarBtn.setVisible(false);
        });
        openLeftBarBtn.setVisible(false);
        openLeftBarBtn.setOnMouseClicked(mouseEvent -> {
            leftPane.setVisible(true);
            openLeftBarBtn.setVisible(false);
            closeLeftBarBtn.setVisible(true);
        });
        fearRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setFearRate(newValue.intValue()));
        foodRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setFoodRate(newValue.intValue()));
        taxRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setTaxRate(newValue.intValue()));
    }

    private void updateMenuValues() {
        popularityLabel.setText(String.valueOf(gameController.getPopularity()));
        fearRateSlider.setValue(gameController.getFearRate());
        foodRateSlider.setValue(gameController.getFoodRate());
        taxRateSlider.setValue(gameController.getTaxRate());
        populationLabel.setText(String.valueOf(gameController.getPopulation()));
        goldLabel.setText(String.valueOf(gameController.getGold()));
        playerLabel.setText(gameController.getCurrentPlayerName());
        roundLabel.setText(String.valueOf(gameController.getRoundNum()));
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

    private void loadMarket() throws IOException {
        marketMenu = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/ShopMenusfxml/shopMenu.fxml"));
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(marketMenu);
    }
}
