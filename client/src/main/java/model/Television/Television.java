package model.Television;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Vector2D;

import java.net.URL;
import java.util.ResourceBundle;

public class Television extends Application implements Initializable {
    public ScrollPane scrollPane;
    public Button stopAndPlayButton;
    private SaveData mainSaveData;
    private static TilePane mainGrid = new TilePane();
    private Timeline timeline;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(Television.class.getResource("/FXML/Gamefxml/televisionMenu.fxml"));
        AnchorPane betPane = (AnchorPane) rootPane.getChildren().get(0);
        ScrollPane scrollPane = (ScrollPane) betPane.getChildren().get(0);
        stage.setScene(new Scene(rootPane));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mainSaveData = (SaveData) ResourceManager.load(SaveData.where() + ".save");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mainGrid.setPrefColumns(100);
        mainGrid.setPrefTileHeight(80);
        mainGrid.setPrefTileWidth(80);
        Vector2D coordinate = new Vector2D(0, 0);
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                coordinate.x = x;
                coordinate.y = y;
                GridPane gridPane = initializeCellGrid(mainSaveData.map[y][x]);
                mainGrid.getChildren().add(gridPane);
                GridPane cellGrid = (GridPane) mainGrid.getChildren().get(x + 100 * y);
                cellGrid.setBackground(new Background(new BackgroundImage(new Image(mainSaveData.map[y][x]),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(1, 1, true, true, false, false))));            }
               // updateCellGrid(x,y,);
        }
        scrollPane.setContent(mainGrid);
        timeline = new Timeline();
        timeline.setCycleCount(-1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), e -> {
            try {
                initializeScrollPane(scrollPane);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.play();
    }

    private void initializeScrollPane(ScrollPane scrollPane) throws Exception {
        SaveData saveData = (SaveData) ResourceManager.load(SaveData.where() + ".save");
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                updateCellGrid(x, y, saveData.map[y][x], mainGrid, saveData);
            }
        }
        scrollPane.setContent(mainGrid);
    }

    private GridPane initializeCellGrid(String cell) {
        GridPane cellGrid = new GridPane();
        Tooltip tooltip = new Tooltip(cell);
        Tooltip.install(cellGrid, tooltip);
        return cellGrid;
    }

    public void load(MouseEvent mouseEvent) throws Exception {
        if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.stop();
            stopAndPlayButton.setText("Play");
        } else {
            timeline.play();
            stopAndPlayButton.setText("Stop");
        }

    }

    public void updateCellGrid(int x, int y, String cell, TilePane mainGrid, SaveData saveData) {
        Vector2D cellCoord = new Vector2D(x, y);
        GridPane cellGrid = (GridPane) mainGrid.getChildren().get(cellCoord.x + 100 * cellCoord.y);
        cellGrid.getChildren().clear();

        int column = 0;
        // for (MapAsset asset : cell.getAllAssets()) {
        if (saveData.buildings[y][x] != null) {
            ImageView imageView = new ImageView(new Image(saveData.buildings[y][x]));
            imageView.setPreserveRatio(true);
            double fitSize = 20;
            //   if (asset instanceof Building || asset instanceof Tree || asset instanceof Cliff)
            fitSize = 80;
            imageView.setFitHeight(fitSize);
            imageView.setFitWidth(fitSize);
            cellGrid.add(imageView, column % 4, column / 4);
            column++;
        }
        //}
    }


}
