package controller.GameControllers;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Game.Game;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.Tree;
import utils.Vector2D;

public class GraphicsController {
    private final TilePane mainGrid;
    private final Map map;
    private Rectangle selectionRect;
    private AnchorPane rootPane;
    private double startX, startY;

    public GraphicsController(Game game) {
        this.map = game.getMap();
        mainGrid = new TilePane();
        game.setGraphicsController(this);
        loadGraphics();
    }

    private void loadGraphics() {
        mainGrid.setPrefColumns(map.getSize().x);
        mainGrid.setPrefTileHeight(80);
        mainGrid.setPrefTileWidth(80);
        mainGrid.setOnMousePressed(this::handleMousePressed);
        mainGrid.setOnMouseDragged(this::handleMouseDragged);
        mainGrid.setOnMouseReleased(this::handleMouseReleased);
        Vector2D coordinate = new Vector2D(0, 0);
        for (int y = 0; y < map.getSize().y; y++) {
            for (int x = 0; x < map.getSize().x; x++) {
                coordinate.x = x;
                coordinate.y = y;
                Cell cell = map.getCell(coordinate);
                GridPane gridPane = initializeCellGrid(cell);
                mainGrid.getChildren().add(gridPane);
                updateCellGrid(cell);
            }
        }
    }

    public void updateCellGrid(Cell cell) {
        Vector2D cellCoord = cell.getCoordinate();
        GridPane cellGrid = (GridPane) mainGrid.getChildren().get(cellCoord.x + map.getSize().y * cellCoord.y);
        cellGrid.setBackground(new Background(new BackgroundImage(cell.getType().getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        cellGrid.getChildren().clear();
        for (MapAsset asset : cell.getAllAssets()) {
            ImageView imageView = new ImageView(asset.getType().getImage());
            double fitSize = 20;
            if (asset instanceof Building || asset instanceof Tree || asset instanceof Cliff)
                fitSize = 80;
            imageView.setFitHeight(fitSize);
            imageView.setFitWidth(fitSize);
            cellGrid.getChildren().add(imageView);
        }
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    private GridPane initializeCellGrid(Cell cell) {
        GridPane cellGrid = new GridPane();
        Tooltip tooltip = new Tooltip(cell.toString());
        Tooltip.install(cellGrid, tooltip);
        return cellGrid;
    }

    public void zoom(double v) {
        double prefHeight = mainGrid.getPrefTileHeight();
        double prefWidth = mainGrid.getPrefTileWidth();
        if (v > 1) {
            if (prefWidth > 120 || prefHeight > 120) return;
        } else if (prefWidth < 60 || prefHeight < 60) return;
        mainGrid.setPrefTileHeight(prefHeight * v);
        mainGrid.setPrefTileWidth(prefWidth * v);
        zoomAllImages(v);
    }

    private void zoomAllImages(double v) {
        for (Node child : mainGrid.getChildren()) {
            for (Node node : ((GridPane) child).getChildren()) {
                ImageView image = (ImageView) node;
                image.setFitWidth(image.getFitWidth() * v);
                image.setFitHeight(image.getFitHeight() * v);
            }
        }
    }

    public TilePane getMainGrid() {
        return mainGrid;
    }

    private void handleMousePressed(MouseEvent event) {
        if (!event.isSecondaryButtonDown()) return;
        startX = event.getX();
        startY = event.getY();

        selectionRect = new Rectangle(startX, startY, 0, 0);
        selectionRect.setOpacity(0);
        rootPane.getChildren().add(selectionRect);
    }

    private void handleMouseDragged(MouseEvent event) {
        if (selectionRect == null) return;
        double currentX = event.getX();
        double currentY = event.getY();

        double minX = Math.min(startX, currentX);
        double minY = Math.min(startY, currentY);
        double maxX = Math.max(startX, currentX);
        double maxY = Math.max(startY, currentY);

        selectionRect.setX(minX);
        selectionRect.setY(minY);
        selectionRect.setWidth(maxX - minX);
        selectionRect.setHeight(maxY - minY);
    }

    private void handleMouseReleased(MouseEvent event) {
        if (selectionRect == null) return;
        Bounds selectionBounds = selectionRect.getBoundsInParent();
        for (int i = 0; i < mainGrid.getChildren().size(); i++) {
            GridPane cellGrid = (GridPane) mainGrid.getChildren().get(i);
            Bounds bounds = cellGrid.getBoundsInParent();
            if (bounds.intersects(selectionBounds))
                cellGrid.setBorder(new Border(new BorderStroke(Color.CYAN, BorderStrokeStyle.DASHED,
                        CornerRadii.EMPTY, BorderStroke.MEDIUM)));
            else
                cellGrid.setBorder(null);
        }
        rootPane.getChildren().remove(selectionRect);
        selectionRect = null;
    }
}
