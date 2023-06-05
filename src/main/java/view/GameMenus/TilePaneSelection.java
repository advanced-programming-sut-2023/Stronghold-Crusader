package view.GameMenus;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TilePaneSelection extends Application {
    private static AnchorPane mainPane;
    private static Rectangle selectionRect;
    private double startX, startY;

    @Override
    public void start(Stage primaryStage) {
        TilePane tilePane = new TilePane();
        mainPane = new AnchorPane(tilePane);
        tilePane.setPrefColumns(4);
        tilePane.setPrefRows(4);
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        for (int i = 0; i < 16; i++) {
            Rectangle rect = new Rectangle(50, 50, Color.BLUE);
            tilePane.getChildren().add(rect);
        }

        tilePane.setOnMousePressed(this::handleMousePressed);
        tilePane.setOnMouseDragged(this::handleMouseDragged);
        tilePane.setOnMouseReleased(this::handleMouseReleased);

        Scene scene = new Scene(mainPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMousePressed(MouseEvent event) {
        if(!event.isSecondaryButtonDown()) return;
        startX = event.getX();
        startY = event.getY();

        selectionRect = new Rectangle(startX, startY, 0, 0);
        selectionRect.setFill(Color.LIGHTBLUE.deriveColor(0, 1, 1, 0.5));
        selectionRect.setStroke(Color.BLUE);
        selectionRect.setStrokeWidth(2);

        mainPane.getChildren().add(selectionRect);
    }

    private void handleMouseDragged(MouseEvent event) {
        if(!event.isSecondaryButtonDown()) return;
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
        if(selectionRect == null) return;
        Bounds selectionBounds = selectionRect.getBoundsInParent();
        TilePane tilePane = (TilePane) event.getSource();

        for (int i = 0; i < tilePane.getChildren().size(); i++) {
            Rectangle rect = (Rectangle) tilePane.getChildren().get(i);
            Bounds bounds = rect.getBoundsInParent();
            if (bounds.intersects(selectionBounds)) {
                rect.setFill(Color.RED);
            }
        }
        mainPane.getChildren().remove(selectionRect);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
