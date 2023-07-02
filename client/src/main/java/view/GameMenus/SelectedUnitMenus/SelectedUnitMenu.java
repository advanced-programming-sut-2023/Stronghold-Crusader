package view.GameMenus.SelectedUnitMenus;

import controller.GameControllers.SelectedUnitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import utils.Vector2D;
import view.GameMenus.GraphicGameMenu;
import view.Main;
import view.enums.messages.GameMessage.SelectedUnitMessage;

import java.io.IOException;
import java.net.URL;

public class SelectedUnitMenu {
    public TilePane selectedUnits;
    private static SelectedUnitController controller;
    private static GraphicGameMenu graphicGameMenu;
    private static Popup popup, moveTypePopup;
    public ImageView attackButton;
    public ImageView moveButton;
    public ImageView patrolButton;

    public static void setController(SelectedUnitController controller) {
        SelectedUnitMenu.controller = controller;
    }

    public static void setGraphicGameMenu(GraphicGameMenu graphicGameMenu) {
        SelectedUnitMenu.graphicGameMenu = graphicGameMenu;
    }

    public void initialize(){
        CoordinatePopupMenu.setMenu(this);
        moveButton.setOnMouseClicked(e -> {
            try {
                openPopup();
                CoordinatePopupMenu.setMode("move");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        attackButton.setOnMouseClicked(e -> {
            try {
                openPopup();
                CoordinatePopupMenu.setMode("attack");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        patrolButton.setOnMouseClicked(e -> {
            try {
                openPopup();
                CoordinatePopupMenu.setMode("patrol");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void openPopup() throws IOException {
        popup = new Popup();
        AnchorPane pane = FXMLLoader.load(
                new URL(SelectedUnitMenu.class.
                        getResource("/FXML/Gamefxml/selectedUnitMenus/coordinatePopup.fxml").toExternalForm()));
        popup.getContent().add(pane);
        popup.show(Main.mainStage);
    }

    public static void openMoveTypePopup() throws IOException {
        moveTypePopup = new Popup();
        AnchorPane pane = FXMLLoader.load(
                new URL(SelectedUnitMenu.class.
                        getResource("/FXML/Gamefxml/selectedUnitMenus/moveTypePopup.fxml").toExternalForm()));
        moveTypePopup.getContent().add(pane);
        moveTypePopup.show(Main.mainStage);
    }


    public static void closeMoveTypePopup(){
        moveTypePopup.hide();
    }
    public static void closePopup(){
        popup.hide();
    }

    public void move(){
        SelectedUnitMessage msg = controller.moveUnit(CoordinatePopupMenu.getFinalCoordinate().x,
                CoordinatePopupMenu.getFinalCoordinate().y);
        graphicGameMenu.printError(msg.getMessage());
    }
    public void attack(){
        SelectedUnitMessage msg = controller.setAttackTarget(CoordinatePopupMenu.getFinalCoordinate().x,
                CoordinatePopupMenu.getFinalCoordinate().y);
        graphicGameMenu.printError(msg.getMessage());
    }
    public void patrol(){
        Vector2D initial = controller.getSelectedUnits().get(0).getCoordinate();
        SelectedUnitMessage msg = controller.petrolUnit(initial.x, initial.y,
                CoordinatePopupMenu.getFinalCoordinate().x, CoordinatePopupMenu.getFinalCoordinate().y);
        graphicGameMenu.printError(msg.getMessage());
    }
}

