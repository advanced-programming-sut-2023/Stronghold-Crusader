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
import model.Map.Map;
import model.Map.MapManager;
import utils.Vector2D;
import view.Main;
import view.UserMenus.MainMenu;

import java.net.URL;
import java.util.ResourceBundle;

public class Television extends Application implements Initializable {
    public ScrollPane scrollPane;
    public Button stopAndPlayButton;
    private static TilePane mainGrid = new TilePane();
    private Timeline timeline;
    private int number = 1;
    private static String ID;
    private static boolean live = false;


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(Television.class.getResource("/FXML/Gamefxml/televisionMenu.fxml"));
        AnchorPane betPane = (AnchorPane) rootPane.getChildren().get(0);
        ScrollPane scrollPane = (ScrollPane) betPane.getChildren().get(0);
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        stage.show();
    }

    public static void setID(String ID) {
        Television.ID = ID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SaveData saveData = TelevisionManager.load("test", number + ".save");
            if (saveData == null) {
                back();
                return;
            }
            Map map = MapManager.load(saveData.mapId);
            loadMap(map);
            number++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (live) goToLive();
        scrollPane.setContent(mainGrid);
        timeline = new Timeline();
        timeline.setCycleCount(-1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), e -> {
            try {
                initializeScrollPane(scrollPane);
                number++;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.play();
    }

    private void goToLive() {
        while (TelevisionManager.load("test", number + ".save") != null){
            number ++;
        }
        number -- ;
    }

    private void loadMap(Map map) {
        mainGrid.setPrefColumns(100);
        mainGrid.setPrefTileHeight(80);
        mainGrid.setPrefTileWidth(80);
        Vector2D coordinate = new Vector2D(0, 0);
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                coordinate.x = x;
                coordinate.y = y;

                GridPane gridPane = initializeCellGrid(map.getCell(coordinate).getType().getImage().toString());
                mainGrid.getChildren().add(gridPane);
                GridPane cellGrid = (GridPane) mainGrid.getChildren().get(x + 100 * y);
                cellGrid.setBackground(new Background(new BackgroundImage((map.getCell(coordinate).getType().getImage()),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(1, 1, true, true, false, false))));
            }
            // updateCellGrid(x,y,);
        }
    }

    private void initializeScrollPane(ScrollPane scrollPane) throws Exception {
        SaveData saveData = TelevisionManager.load("test", number + ".save");
        if (saveData == null) {
            if (!live) timeline.stop();
            number --;
            return;
        }
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                updateCellGrid(x, y, mainGrid, saveData);
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

    public static void setLive(boolean live) {
        Television.live = live;
    }

    public void updateCellGrid(int x, int y, TilePane mainGrid, SaveData saveData) {
        Vector2D cellCoord = new Vector2D(x, y);
        GridPane cellGrid = (GridPane) mainGrid.getChildren().get(cellCoord.x + 100 * cellCoord.y);
        cellGrid.getChildren().clear();
        int column = 0;
        if (saveData.assets[x][y] != null) {
            ImageView imageView = new ImageView(new Image(saveData.assets[y][x]));
            imageView.setPreserveRatio(true);
            double fitSize = 80;
            imageView.setFitHeight(fitSize);
            imageView.setFitWidth(fitSize);
            cellGrid.add(imageView, 0, 0);
            column++;
        }
//        if (saveData.assets[1][y][x] != null) {
//            double size = 20;
//            while (column < 5) {
//                ImageView soldier = new ImageView(new Image(saveData.assets[1][y][x]));
//                soldier.setPreserveRatio(true);
//                soldier.setFitWidth(size);
//                soldier.setFitHeight(size);
//                cellGrid.add(soldier, column % 4, column / 4);
//                column ++;
//            }
        //}

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(Main.mainStage);
    }
    public void back() throws Exception {
        new MainMenu().start(Main.mainStage);
    }
}
