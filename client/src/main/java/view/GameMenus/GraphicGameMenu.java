package view.GameMenus;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import controller.GameControllers.MarketController;
import controller.GameControllers.TradeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game.Governance;
import model.Map.Cell;
import model.MapAsset.Building.Building;
import model.MapAsset.MapAsset;
import view.GameMenus.MarketMenus.MarketMenu;
import view.GameMenus.TradeMenus.TradeMenu;

import java.io.IOException;

public class GraphicGameMenu extends Application {
    private static Stage stage;
    private static GameController gameController;
    private static GraphicsController graphicsController;
    private static AnchorPane rootPane;
    public ScrollPane mainScrollPane;
    public AnchorPane bottomPane, leftPane, selectedUnitMenu;
    public Label popularityLabel, populationLabel, goldLabel, roundLabel, playerLabel,
            foodPopularity, religionPopularity, innPopularity, nextPopularityNum, fearPopularity,
            taxPopularity, errorMessageText;
    public Slider fearRateSlider, foodRateSlider, taxRateSlider;
    public ImageView closeLeftBarBtn, openLeftBarBtn, minimap, religionFace, taxFace, innFace, closePopularityBarBtn,
            fearFace, foodFace, backToDropButton, marketBtn, selectedUnitButton;
    public AnchorPane dropBuildingMenu, marketMenu, popularityMenu, mercenaryPostMenu;

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
    public void initialize() throws IOException {
        initializeScrollPane();
        initializeMinimap();
        initializeLeftPane();
        initializePopularityMenu();
        initializeMarket();
        initializeDropBuildingMenu();
        updateGovernmentMenuValues();
        updatePopularityMenuValues();
    }

    private void initializeScrollPane() {
        mainScrollPane.setContent((graphicsController.getMainGrid()));
        mainScrollPane.setOnKeyPressed(this::handleKeyPressed);
        mainScrollPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) mainScrollPane.setPannable(false);
        });
        mainScrollPane.setOnMouseReleased(mouseEvent -> mainScrollPane.setPannable(true));
    }

    private void initializePopularityMenu() {
        popularityMenu.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null)));
        popularityMenu.setVisible(false);
        closePopularityBarBtn.setOnMouseClicked(mouseEvent -> popularityMenu.setVisible(false));
    }

    private void initializeMinimap() {
        minimap.setOnMouseClicked(mouseEvent -> {
            double xRatio = mouseEvent.getX() / minimap.getBoundsInLocal().getWidth();
            double yRatio = mouseEvent.getY() / minimap.getBoundsInLocal().getHeight();
            mainScrollPane.setVvalue(xRatio);
            mainScrollPane.setHvalue(yRatio);
        });
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
            selectedUnitButton.setVisible(gameController.isBuildingSelected());
            openLeftBarBtn.setVisible(false);
            closeLeftBarBtn.setVisible(true);
        });
        fearRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setFearRate(newValue.intValue()));
        foodRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setFoodRate(newValue.intValue()));
        taxRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> gameController.setTaxRate(newValue.intValue()));
    }

    private void updateGovernmentMenuValues() {
        popularityLabel.setText(String.valueOf(gameController.getPopularity()));
        fearRateSlider.setValue(gameController.getFearRate());
        foodRateSlider.setValue(gameController.getFoodRate());
        taxRateSlider.setValue(gameController.getTaxRate());
        populationLabel.textProperty().bind(Bindings.convert(gameController.getPeasentPopulation()));
        goldLabel.textProperty().bind(Bindings.convert(gameController.getGold()));
        playerLabel.setText(gameController.getCurrentPlayerName());
        roundLabel.setText(String.valueOf(gameController.getRoundNum()));
    }

    private void updatePopularityMenuValues() {
        Governance governance = gameController.getCurrentPlayer().getGovernance();
        Image happyImage = new Image(GraphicGameMenu.class.getResource("/assets/icons/happy.png").toExternalForm());
        Image angryImage = new Image(GraphicGameMenu.class.getResource("/assets/icons/angry.png").toExternalForm());
        Image normalImage = new Image(GraphicGameMenu.class.getResource("/assets/icons/normal.png").toExternalForm());
        int foodPopularityNum = governance.getFoodPopularity();
        int fearPopularityNum = governance.getFearPopularity();
        int innPopularityNum = governance.getInnPopularity();
        int taxPopularityNum = governance.getTaxPopularity();
        int religionPopularityNum = governance.getReligionPopularity();
        foodPopularity.setText(String.valueOf(foodPopularityNum));
        setFaceImage(foodFace, happyImage, angryImage, normalImage, foodPopularityNum);
        fearPopularity.setText(String.valueOf(fearPopularityNum));
        setFaceImage(fearFace, happyImage, angryImage, normalImage, fearPopularityNum);
        innPopularity.setText(String.valueOf(innPopularityNum));
        setFaceImage(innFace, happyImage, angryImage, normalImage, innPopularityNum);
        religionPopularity.setText(String.valueOf(religionPopularityNum));
        setFaceImage(religionFace, happyImage, angryImage, normalImage, religionPopularityNum);
        taxPopularity.setText(String.valueOf(taxPopularityNum));
        setFaceImage(taxFace, happyImage, angryImage, normalImage, taxPopularityNum);
        nextPopularityNum.setText(String.valueOf(governance.getTotalPopularity()));
    }

    private void setFaceImage(ImageView faceImage, Image happyImage, Image angryImage, Image normalImage, int foodPopularityNum) {
        if (foodPopularityNum > 0) faceImage.setImage(happyImage);
        else if (foodPopularityNum == 0) faceImage.setImage(normalImage);
        else faceImage.setImage(angryImage);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case EQUALS:
                graphicsController.zoom(1.2);
                break;
            case MINUS:
                graphicsController.zoom(0.8);
                break;
            case C:
                if (keyEvent.isControlDown())
                    graphicsController.copySelectedBuildings();
                break;
            case V:
                if (keyEvent.isControlDown())
                    graphicsController.pasteSelectedBuildings();
                break;
        }
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

    public void initializeMarket() throws IOException {
        marketMenu = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/ShopMenusfxml/shopMenu.fxml"));
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(marketMenu);
        MarketMenu.marketController = new MarketController(gameController.getCurrentPlayer());
    }

    public void openPopularity() {
        popularityMenu.setVisible(true);
    }

    public void printError(String text) {
        Platform.runLater(() -> {
            errorMessageText = new Label();
            errorMessageText.setLayoutX(285);
            errorMessageText.setLayoutY(595);
            errorMessageText.setPrefHeight(3);
            errorMessageText.setPrefWidth(474);
            errorMessageText.setTextFill(Color.WHITE);
            errorMessageText.setText(text);
            rootPane.getChildren().add(errorMessageText);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> {
                        rootPane.getChildren().remove(errorMessageText);
                    })
            );
            timeline.setCycleCount(1);
            timeline.play();
        });
    }

    public void openTradeMenu(MouseEvent mouseEvent) throws Exception {
        TradeMenu.setTradeController(new TradeController(gameController.getGame()));
        new TradeMenu().start(new Stage());
    }

    public void nextTurn() throws Exception {
//        String[][] map = new String[graphicsController.getMap().getSize().y][graphicsController.getMap().getSize().x];
//        String[][] buildings = new String[graphicsController.getMap().getSize().y][graphicsController.getMap().getSize().x];
//        loadCells(map, buildings);
//        ResourceManager.save(new SaveData(graphicsController.getMap().getSize().x, graphicsController.getMap().getSize().y
//                , map, buildings),  SaveData.getNumber() + ".save");
        gameController.nextTurn();
        updateGovernmentMenuValues();
        updatePopularityMenuValues();
    }

    private void loadCells(String[][] map, String[][] buildings) {
//        for (int y = 0; y < graphicsController.getMap().getSize().y; y++) {
//            for (int x = 0; x < graphicsController.getMap().getSize().x; x++) {
//                Vector2D coordinate = new Vector2D(x, y);
//                Cell cell = graphicsController.getMap().getCell(coordinate);
//                map[y][x] = cell.getType().getImage().getUrl().toString();
//                Building building = searchForBuildings(cell);
//                if (building == null) buildings[y][x] = null;
//                else buildings[y][x] = building.getType().getImage().getUrl().toString();
//            }
//        }
    }

    private Building searchForBuildings(Cell cell) {
        for (MapAsset asset : cell.getAllAssets()) {
            if (asset instanceof Building) return (Building) asset;
        }
        return null;
    }


}
