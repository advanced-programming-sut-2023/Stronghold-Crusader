package view.GameMenus.SelectedUnitMenus;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utils.Vector2D;

import java.io.IOException;

public class MoveTypeMenu {

    private static String mode;
    public ImageView move;
    public ImageView attack;
    public ImageView petrol;

    public static void setMode(String mode) {
        MoveTypeMenu.mode = mode;
    }

    public void initialize(){
        move.setOnMouseClicked(e -> {
            setMode("move");
            performAction();
        });
        attack.setOnMouseClicked(e -> {
            setMode("attack");
            performAction();
        });
        petrol.setOnMouseClicked(e -> {
            setMode("petrol");
            performAction();
        });
    }

    private void performAction(){
        switch (mode){
            case "move":
                CoordinatePopupMenu.getMenu().move();
                break;
            case "attack":
                CoordinatePopupMenu.getMenu().attack();
                break;
            case "petrol":
                CoordinatePopupMenu.getMenu().patrol();
                break;
        }
        SelectedUnitMenu.closeMoveTypePopup();
    }
}
