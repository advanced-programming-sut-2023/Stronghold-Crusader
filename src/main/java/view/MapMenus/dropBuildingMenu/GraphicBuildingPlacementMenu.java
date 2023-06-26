package view.MapMenus.dropBuildingMenu;

import controller.MapControllers.BuildingPlacementController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        controller.setBuildingCategory("production");
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
        HBox pane = new HBox();
        BuildingCategory buildingCategory = BuildingCategory.getCategory(category);
        ArrayList<MapAssetType> buildings = BuildingType.getAllBuildings(buildingCategory);
        int i = 0;
        for (MapAssetType type : buildings) {
            VBox vBox = new VBox();
            ImageView imageView = new ImageView();
            imageView.setImage(type.getImage());
            Text text = new Text(type.name().toLowerCase());
            text.setTextAlignment(TextAlignment.CENTER);
            text.setWrappingWidth(70);
            text.setFont(new Font(9));
            vBox.getChildren().addAll(imageView, text);
            vBox.setAlignment(Pos.CENTER);
            pane.getChildren().add(vBox);
            imageView.setFitHeight(60);
            imageView.setPreserveRatio(true);
            setDropBuilding(imageView, pane);
            i++;
        }
        pane.setSpacing(15);
        pane.setPrefHeight(65);
        pane.setStyle("-fx-background-color: transparent");
        pane.setAlignment(Pos.CENTER_LEFT);
        scrollPane.setContent(pane);
    }

    private void setDropBuilding(ImageView building, HBox pane){
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
