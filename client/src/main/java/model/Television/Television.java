package model.Television;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Television extends Application {
    public ScrollPane mainGrid;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(Television.class.getResource("/FXML/Gamefxml/televisionMenu.fxml"));
        AnchorPane betPane = (AnchorPane) rootPane.getChildren().get(0);
        ScrollPane scrollPane = (ScrollPane) betPane.getChildren().get(0);
        initializeScrollPane(scrollPane);
        stage.setScene(new Scene(rootPane));
        stage.show();
    }

    private void initializeScrollPane(ScrollPane scrollPane) throws Exception {
        SaveData saveData = (SaveData) ResourceManager.load( SaveData.where() +".save");
        TilePane mainGrid = new TilePane();
        mainGrid.setPrefColumns(saveData.x);
        mainGrid.setPrefTileHeight(80);
        mainGrid.setPrefTileWidth(80);
        for (int y = 0; y < saveData.y; y++) {
            for (int x = 0; x < saveData.x; x++) {
                GridPane gridPane = initializeCellGrid(saveData.buildings[y][x]);
                mainGrid.getChildren().add(gridPane);
            }
        }
        scrollPane.setContent(mainGrid);
    }
    private GridPane initializeCellGrid(String name) {
        GridPane cellGrid = new GridPane();
        Tooltip tooltip = new Tooltip(name);
        Tooltip.install(cellGrid, tooltip);

        return cellGrid;
    }

    public void load(MouseEvent mouseEvent) throws Exception {
        if (SaveData.getNumber() == 0) return;
        initializeScrollPane(mainGrid);

    }
}
