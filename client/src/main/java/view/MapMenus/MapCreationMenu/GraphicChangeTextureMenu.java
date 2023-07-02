package view.MapMenus.MapCreationMenu;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.enums.CellType;

public class GraphicChangeTextureMenu {
    public ScrollPane scrollPane;

    @FXML
    public void initialize() {
        loadScrollPane();
    }

    private void loadScrollPane() {
        HBox pane = new HBox();
        CellType[] cellTypes = CellType.values();
        for (CellType type : cellTypes) {
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
            setLoadType(imageView);
        }
        pane.setSpacing(15);
        pane.setPrefHeight(65);
        pane.setStyle("-fx-background-color: transparent");
        pane.setAlignment(Pos.CENTER_LEFT);
        scrollPane.setContent(pane);
    }

    private void setLoadType(ImageView cellType){
        cellType.setOnDragDetected(event -> {
            Dragboard dragboard = scrollPane.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            String draggedText = cellType.getImage().getUrl();
            content.putString(draggedText);
            dragboard.setContent(content);
            event.consume();
        });
    }
}
