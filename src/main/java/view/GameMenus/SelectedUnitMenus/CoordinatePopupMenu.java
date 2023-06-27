package view.GameMenus.SelectedUnitMenus;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.Vector2D;

import java.io.IOException;

public class CoordinatePopupMenu {

    public Text error;
    private static String errorText;
    public TextField xCord;
    public TextField yCord;
    private static Vector2D finalCoordinate;
    private static SelectedUnitMenu menu;
    private static String mode;

    public static Vector2D getFinalCoordinate() {
        return finalCoordinate;
    }

    public static void setMenu(SelectedUnitMenu menu) {
        CoordinatePopupMenu.menu = menu;
    }

    public static SelectedUnitMenu getMenu() {
        return menu;
    }

    public static void setMode(String mode) {
        CoordinatePopupMenu.mode = mode;
    }

    public void back() throws IOException {
        finalCoordinate = new Vector2D(Integer.parseInt(xCord.getText()), Integer.parseInt(yCord.getText()));
        switch (mode){
            case "move":
                menu.move();
                break;
            case "attack":
                menu.attack();
                break;
            case "patrol":
                menu.patrol();
                break;
        }
        SelectedUnitMenu.closePopup();
    }


}
