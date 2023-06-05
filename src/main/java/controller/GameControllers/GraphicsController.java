package controller.GameControllers;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import model.Game.Game;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.MapAsset;
import utils.Vector2D;

public class GraphicsController {
    private final TilePane mainGrid;
    private final Map map;

    public GraphicsController(Game game) {
        this.map = game.getMap();
        mainGrid = new TilePane();
        loadGraphics();
    }

    private void loadGraphics() {
        mainGrid.setPrefColumns(map.getSize().x);
        mainGrid.setPrefTileHeight(80);
        mainGrid.setPrefTileWidth(80);
        Vector2D coordinate = new Vector2D(0, 0);
        for (int y = 0; y < map.getSize().y; y++) {
            for (int x = 0; x < map.getSize().x; x++) {
                coordinate.x = x;
                coordinate.y = y;
                Cell cell = map.getCell(coordinate);
                mainGrid.getChildren().add(initializeCellGrid(cell));
//                updateCellGrid(cellGrid, cell);
            }
        }
    }

    private GridPane initializeCellGrid(Cell cell) {
        GridPane cellGrid = new GridPane();
        cellGrid.setBackground(new Background(new BackgroundImage(cell.getType().getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Tooltip tooltip = new Tooltip(cell.toString());
        Tooltip.install(cellGrid, tooltip);
        return cellGrid;
    }

    public void zoom(double v) {
        double prefHeight = mainGrid.getPrefTileHeight();
        double prefWidth = mainGrid.getPrefTileWidth();
        if (v > 1) {
            if (prefWidth > 140 || prefHeight > 140) return;
        } else if (prefWidth < 60 || prefHeight < 60) return;
        mainGrid.setPrefTileHeight(prefHeight * v);
        mainGrid.setPrefTileWidth(prefWidth * v);
    }

    private void updateCellGrid(GridPane cellGrid, Cell cell) {
        cellGrid.getChildren().clear();
        for (MapAsset asset : cell.getAllAssets())
            cellGrid.getChildren().add(new ImageView(asset.getType().getImage()));
    }

    public TilePane getMainGrid() {
        return mainGrid;
    }
}
