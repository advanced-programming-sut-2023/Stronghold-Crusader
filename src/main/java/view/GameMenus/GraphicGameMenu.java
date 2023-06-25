package view.GameMenus;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import controller.GameControllers.MarketController;
import javafx.application.Application;
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
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game.Governance;
import view.GameMenus.MarketMenus.MarketMenu;

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
    public ImageView minimap;
    public AnchorPane popularityMenu;
    public ImageView foodFace;
    public Label foodPopularity;
    public ImageView religionFace;
    public Label religionPopularity;
    public ImageView taxFace;
    public Label taxPopularity;
    public ImageView fearFace;
    public Label fearPopularity;
    public ImageView innFace;
    public Label innPopularity;
    public ImageView closePopularityBarBtn;
    public Label nextPopularityNum;

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
        graphicsController.setSelectedUnitsMenu(selectedUnitsMenu);
        mainScrollPane.setContent((graphicsController.getMainGrid()));
        mainScrollPane.setOnKeyPressed(this::handleKeyPressed);
        mainScrollPane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) mainScrollPane.setPannable(false);
        });
        mainScrollPane.setOnMouseReleased(mouseEvent -> mainScrollPane.setPannable(true));
        initializeMinimap();
        initializeLeftPane();
        initializePopularityMenu();
        loadDropBuildingMenu();
        loadMarket();
        updateGovernmentMenuValues();
        updatePopularityMenuValues();
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
        populationLabel.setText(String.valueOf(gameController.getPopulation()));
        goldLabel.setText(String.valueOf(gameController.getGold().get()));
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
        MarketMenu.marketController = new MarketController(gameController.getCurrentPlayer());
    }

    public void openPopularity() {
        popularityMenu.setVisible(true);
    }
}
