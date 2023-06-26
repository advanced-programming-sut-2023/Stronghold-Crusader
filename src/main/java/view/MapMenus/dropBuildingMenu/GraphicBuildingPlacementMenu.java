package view.MapMenus.dropBuildingMenu;

import controller.MapControllers.BuildingPlacementController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.BuildingType;
import model.enums.AssetType.MapAssetType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicBuildingPlacementMenu {
    public static BuildingPlacementController controller;
    public ImageView entranceCategoryButton, trainingCategoryButton, normalCategoryButton, storageCategoryButton,
            defenseCategoryButton, productionCategoryButton;
    public ScrollPane scrollPane;

    public static void setController(BuildingPlacementController controller) {
        GraphicBuildingPlacementMenu.controller = controller;
    }

    @FXML
    public void initialize() throws IOException {
        setCategoryButton(entranceCategoryButton);
        setCategoryButton(trainingCategoryButton);
        setCategoryButton(normalCategoryButton);
        setCategoryButton(storageCategoryButton);
        setCategoryButton(defenseCategoryButton);
        setCategoryButton(productionCategoryButton);
        loadScrollPane("production");
    }

    private void setCategoryButton(ImageView button) {
        button.setOnMouseClicked(e -> {
            String path = button.getImage().getUrl();
            Pattern pattern = Pattern.compile("dropBuildingIcons/(?<name>\\S+)\\.png");
            Matcher matcher = pattern.matcher(path);
            matcher.find();
            String category = matcher.group("name");
            controller.setBuildingCategory(category);
            try {
                loadScrollPane(category);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void loadScrollPane(String category) throws IOException {
        StackPane pane = new StackPane();
        BuildingCategory buildingCategory = BuildingCategory.getCategory(category);
        ArrayList<MapAssetType> buildings = BuildingType.getAllBuildings(buildingCategory);
        int i = 0;
        for (MapAssetType type : buildings) {
            ImageView imageView = new ImageView();
            imageView.setImage(type.getImage());
            pane.getChildren().add(imageView);
            imageView.setTranslateY(3);
            imageView.setTranslateX(70 * i);
            imageView.setFitHeight(60);
            imageView.setPreserveRatio(true);
            setDropBuilding(imageView, pane);
            i++;
        }
        pane.setPrefWidth(i * 70);
        pane.setPrefHeight(65);
        pane.setStyle("-fx-background-color: transparent");
        pane.setAlignment(Pos.CENTER_LEFT);
        scrollPane.setContent(pane);
    }

    private void setDropBuilding(ImageView building, StackPane pane){
        AtomicReference<Double> mouseAnchorX = new AtomicReference<>((double) 0);
        AtomicReference<Double> mouseAnchorY = new AtomicReference<>((double) 0);

        building.setOnMousePressed(mouseEvent -> {
            mouseAnchorX.set(mouseEvent.getSceneX());
            mouseAnchorY.set(mouseEvent.getSceneY());
        });

        building.setOnMouseDragged(mouseEvent -> {
            ImageView imageView = new ImageView();
            imageView.setImage(building.getImage());
            pane.getChildren().add(imageView);
            imageView.setTranslateY(building.getTranslateY());
            imageView.setTranslateX(building.getTranslateX());
            imageView.setFitHeight(60);
            imageView.setPreserveRatio(true);
            setDropBuilding(imageView, pane);
            building.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX.get());
            building.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY.get());
        });

        building.setOnDragDetected(event -> {
            pane.getChildren().remove(building);
            Dragboard dragboard = scrollPane.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            String draggedText = building.getImage().getUrl();
            content.putString(draggedText);
            dragboard.setContent(content);
            event.consume();
        });
    }
}
