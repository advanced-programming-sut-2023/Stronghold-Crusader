package controller.GameControllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game.Game;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.MapAsset.Tree;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;
import view.GameMenus.GraphicGameMenu;
import view.GameMenus.SelectedBuildingMenu;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;
import view.enums.messages.GameMessage.GameMenuMessage;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicsController {
    private final GameController gameController;
    private final TilePane mainGrid;
    private final Map map;
    private GraphicGameMenu gameMenu;
    private Rectangle selectionRect;
    private AnchorPane rootPane, selectedUnitMenu;
    private double startX, startY;
    private final ArrayList<Building> selectedBuildings;
    private Cell lastSelectedCell;

    public GraphicsController(GameController gameController, Game game, GraphicGameMenu gameMenu) {
        this.gameController = gameController;
        gameController.setGraphicsController(this);
        this.map = game.getMap();
        this.gameMenu = gameMenu;
        mainGrid = new TilePane();
        selectedBuildings = new ArrayList<>();
        game.setGraphicsController(this);
        loadGraphics();
    }

    public GraphicsController(Game game) {
        this.gameController = null;
        this.map = game.getMap();
        mainGrid = new TilePane();
        selectedBuildings = new ArrayList<>();
        game.setGraphicsController(this);
        loadGraphics();
    }

    public void loadGraphics() {
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

    public void copySelectedBuildings() {
        Gson gson = new Gson();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        JsonArray jsonArray = new JsonArray();
        for (Building building : selectedBuildings){
            JsonObject buildingInfo = new JsonObject();
            buildingInfo.add("coord", gson.toJsonTree(building.getCoordinate()));
            buildingInfo.add("type", gson.toJsonTree(building.getType()));
            jsonArray.add(buildingInfo);
        }
        clipboard.setContents(new StringSelection(gson.toJson(jsonArray)), null);
    }

    public void pasteSelectedBuildings() {
        if (lastSelectedCell == null)
            return;
        Gson gson = new Gson();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String clipboardText = "";
        try {
            clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            if (!clipboardText.startsWith("[{")) {
                gameMenu.printError("No buildings copied");
                return;
            }
        } catch (UnsupportedFlavorException | IOException e) {
            gameMenu.printError("No buildings copied");
            e.printStackTrace();
        }
        JsonArray jsonArray = gson.fromJson(clipboardText, JsonArray.class);
        Vector2D topLeftCoordinate = new Vector2D(map.getSize().x, map.getSize().y);
        ArrayList<Vector2D> copiedCoords = new ArrayList<>();
        ArrayList<MapAssetType> copiedTypes = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject buildingInfo = gson.fromJson(jsonElement, JsonObject.class);
            Vector2D buildingCoord = gson.fromJson(buildingInfo.get("coord"), Vector2D.class);
            copiedCoords.add(buildingCoord);
            copiedTypes.add(gson.fromJson(buildingInfo.get("type"), MapAssetType.class));
            if (topLeftCoordinate.x > buildingCoord.x)
                topLeftCoordinate.x = buildingCoord.x;
            if (topLeftCoordinate.y > buildingCoord.y)
                topLeftCoordinate.y = buildingCoord.y;
        }
        for (int i = 0; i < copiedCoords.size(); i++) {
            BuildingPlacementMessage msg = GraphicBuildingPlacementMenu.controller.dropBuilding(
                    copiedTypes.get(i).name().toLowerCase(),
                    lastSelectedCell.getCoordinate().x + copiedCoords.get(i).x - topLeftCoordinate.x,
                    lastSelectedCell.getCoordinate().y + copiedCoords.get(i).y - topLeftCoordinate.y,
                    true);
            if (!msg.equals(BuildingPlacementMessage.BUILDING_DROP_SUCCESS)) {
                gameMenu.printError(msg.getMessage());
            }
        }
    }

    public void updateCellGrid(Cell cell) {
        Vector2D cellCoord = cell.getCoordinate();
        GridPane cellGrid = (GridPane) mainGrid.getChildren().get(cellCoord.x + map.getSize().x * cellCoord.y);
        cellGrid.setBackground(new Background(new BackgroundImage(cell.getType().getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        cellGrid.getChildren().clear();
        Tooltip tooltip = new Tooltip(cell.toString());
        Tooltip.install(cellGrid, tooltip);
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
        this.selectedUnitMenu = (AnchorPane) rootPane.getChildren().get(14);
    }

    private GridPane initializeCellGrid(Cell cell) {
        GridPane cellGrid = new GridPane();
        Tooltip tooltip = new Tooltip(cell.toString());
        Tooltip.install(cellGrid, tooltip);
        cellGrid.setOnDragDropped(e -> {
            Dragboard dragboard = e.getDragboard();
            if (dragboard.hasString()) {
                String path = dragboard.getString();
                Pattern pattern = Pattern.compile("/assets/graphic/buildings/(?<name>\\S+)\\.png");
                Matcher matcher = pattern.matcher(path);
                //noinspection ResultOfMethodCallIgnored
                matcher.find();
                BuildingPlacementMessage msg = GraphicBuildingPlacementMenu.controller.dropBuilding(
                        MapAssetType.getTypeBySerial(Integer.parseInt(matcher.group("name"))).name().toLowerCase(),
                        cell.getCoordinate().x, cell.getCoordinate().y, false);
                if (!msg.equals(BuildingPlacementMessage.BUILDING_DROP_SUCCESS)) {
                    gameMenu.printError(msg.getMessage());
                }
            }
            e.consume();
        });
        cellGrid.setOnMouseClicked(mouseEvent -> {
            resetSelection();
            try {
                selectCell(cellGrid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return cellGrid;
    }

    private void removeAllSelectedBorders() {
        for (int i = 0; i < mainGrid.getChildren().size(); i++) {
            GridPane cellGrid = (GridPane) mainGrid.getChildren().get(i);
            cellGrid.setBorder(null);
        }
    }

    private void selectCell(GridPane cellGrid) throws IOException {
        lastSelectedCell = getCellOfNode(cellGrid);
        GameMenuMessage result = gameController.selectBuilding(lastSelectedCell.getCoordinate().x, lastSelectedCell.getCoordinate().y);
        cellGrid.setBorder(new Border(new BorderStroke(Color.CYAN, BorderStrokeStyle.DASHED,
                CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        if (result == GameMenuMessage.BUILDING_SELECTED) {
            runSelectedBuilding();
        }
        SelectedUnitController unitController = gameController.getSelectedUnitController();
        result = gameController.selectUnit(lastSelectedCell.getCoordinate().x, lastSelectedCell.getCoordinate().y);
        if (result == GameMenuMessage.UNIT_SELECTED) {
            loadSelectedUnitMenu();
            TilePane tilePane = (TilePane) ((AnchorPane) selectedUnitMenu.getChildren().get(0)).getChildren().get(0);
            for (MobileUnit unit : unitController.getSelectedUnits())
                tilePane.getChildren().add(createUnitSelectionItem(unit, unitController));
        }
    }

    private void runSelectedBuilding() throws IOException {
        SelectedBuildingController buildingController = gameController.getSelectedBuildingController();
        selectedBuildings.add(buildingController.getBuilding());
        SelectedBuildingMenu.setSelectedBuildingController(buildingController);
        SelectedBuildingMenu.setGameMenu(this.gameMenu);
        loadSelectedBuildingFxml(buildingController.getBuilding().getType());
    }

    private void resetSelection() {
        removeAllSelectedBorders();
        gameController.deselectUnits();
        selectedUnitMenu.getChildren().clear();
        selectedBuildings.clear();
    }

    private void loadSelectedBuildingFxml(MapAssetType type) throws IOException {
        AnchorPane buttonPane = (AnchorPane) rootPane.getChildren().get(4);
        buttonPane.getChildren().clear();
        AnchorPane selectedBuilding = FXMLLoader.load(GraphicGameMenu.class
                .getResource("/FXML/Gamefxml/selectedBuildingMenus/selectedBuilding.fxml"));
        buttonPane.getChildren().add(selectedBuilding);
        if (type.equals(MapAssetType.MERCENARY_POST)) loadMercenaryPost(selectedBuilding);
        else if (type.equals(MapAssetType.BARRACK)) loadBarrack(selectedBuilding);
        else if (type.equals(MapAssetType.ENGINEER_GUILD)) loadEngineerGuild(selectedBuilding);
        else if (type.equals(MapAssetType.TUNNELER_POST)) loadTunnelerPost(selectedBuilding);
        else if (type.equals(MapAssetType.CATHEDRAL)) loadChruch(selectedBuilding);
    }

    private void loadChruch(AnchorPane selectedBuilding) throws IOException {
        selectedBuilding.getChildren().clear();
        AnchorPane engineersGuild = FXMLLoader.load(GraphicGameMenu.class.getResource
                ("/FXML/Gamefxml/selectedBuildingMenus/church.fxml"));
        engineersGuild.setLayoutX(0);
        engineersGuild.setLayoutY(0);
        selectedBuilding.getChildren().add(engineersGuild);
    }

    private void loadTunnelerPost(AnchorPane selectedBuilding) throws IOException {
        selectedBuilding.getChildren().clear();
        AnchorPane engineersGuild = FXMLLoader.load(GraphicGameMenu.class.getResource
                ("/FXML/Gamefxml/selectedBuildingMenus/tunnelerPost.fxml"));
        engineersGuild.setLayoutX(0);
        engineersGuild.setLayoutY(0);
        selectedBuilding.getChildren().add(engineersGuild);
    }

    private void loadEngineerGuild(AnchorPane buttonPane) throws IOException {
        buttonPane.getChildren().clear();
        AnchorPane engineersGuild = FXMLLoader.load(GraphicGameMenu.class.getResource
                ("/FXML/Gamefxml/selectedBuildingMenus/engineersGuild.fxml"));
        engineersGuild.setLayoutX(0);
        engineersGuild.setLayoutY(0);
        buttonPane.getChildren().add(engineersGuild);
    }

    private void loadBarrack(AnchorPane buttonPane) throws IOException {
        buttonPane.getChildren().clear();
        AnchorPane barrack = FXMLLoader.load(GraphicGameMenu.class.getResource
                ("/FXML/Gamefxml/selectedBuildingMenus/barrack.fxml"));
        barrack.setLayoutX(0);
        barrack.setLayoutY(0);
        buttonPane.getChildren().add(barrack);
    }

    private void loadMercenaryPost(AnchorPane buttonPane) throws IOException {
        buttonPane.getChildren().clear();
        AnchorPane mercenaryPost = FXMLLoader.load(GraphicGameMenu.class.getResource
                ("/FXML/Gamefxml/selectedBuildingMenus/mercenaryPost.fxml"));
        mercenaryPost.setLayoutX(0);
        mercenaryPost.setLayoutY(0);
        buttonPane.getChildren().add(mercenaryPost);
    }

    private HBox createUnitSelectionItem(MobileUnit unit, SelectedUnitController unitController) {
        ImageView unitImage = new ImageView(unit.getType().getImage());
        unitImage.setFitHeight(30);
        unitImage.setFitWidth(30);
        ImageView deselectedImage = new ImageView(new Image(GraphicsController.class.getResource("/assets/icons/red.png").toExternalForm()));
        deselectedImage.setFitWidth(30);
        deselectedImage.setFitHeight(30);
        deselectedImage.setVisible(false);
        ImageView selectedImage = new ImageView(new Image(GraphicsController.class.getResource("/assets/icons/green.png").toExternalForm()));
        selectedImage.setFitWidth(30);
        selectedImage.setFitHeight(30);
        deselectedImage.setOnMouseClicked(mouseEvent -> {
            selectedImage.setVisible(true);
            deselectedImage.setVisible(false);
            unitController.addUnit(unit);
        });
        selectedImage.setOnMouseClicked(mouseEvent -> {
            selectedImage.setVisible(false);
            deselectedImage.setVisible(true);
            unitController.removeUnit(unit);
        });
        return new HBox(unitImage, selectedImage, deselectedImage);
    }

    private Cell getCellOfNode(GridPane cellGrid) {
        int index = mainGrid.getChildren().indexOf(cellGrid);
        Vector2D coordinate = new Vector2D(index % map.getSize().x, index / map.getSize().x);
        return map.getCell(coordinate);
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
        resetSelection();
        startX = event.getX();
        startY = event.getY();
        selectedBuildings.clear();
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
            if (bounds.intersects(selectionBounds)) {
                try {
                    selectCell(cellGrid);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        rootPane.getChildren().remove(selectionRect);
        selectionRect = null;
    }

    public void addTransition(MobileUnit unit, Vector2D source, Vector2D dest){
        GridPane initialCellGrid = (GridPane) mainGrid.getChildren().get(source.x + map.getSize().x * source.y);
        GridPane finalCellGrid = (GridPane) mainGrid.getChildren().get(dest.x + map.getSize().x * dest.y);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        for (Node node : initialCellGrid.getChildren()) {
            if (node instanceof ImageView){
                String path = ((ImageView) node).getImage().getUrl();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(path);
                int ordinal;
                if (matcher.find()) {
                    ordinal = Integer.parseInt(matcher.group());
                    MapAssetType type = MapAssetType.getTypeBySerial(ordinal);
                    if (type.equals(unit.getType())){
                        transition.setNode(node);
                        break;
                    }
                }
            }
        }
        transition.setToX(finalCellGrid.getTranslateX());
        transition.setToY(finalCellGrid.getTranslateY());
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.play();
    }

    public void loadSelectedUnitMenu() throws IOException {
        System.out.println("loading");
        selectedUnitMenu.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(GraphicGameMenu.class.
                getResource("/FXML/Gamefxml/selectedUnitMenus/selectedUnitMain.fxml"));
        selectedUnitMenu.getChildren().add(pane);
    }
}
