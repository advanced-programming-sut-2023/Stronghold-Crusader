package view.GameMenus.MarketMenus;

import controller.GameControllers.MarketController;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Game.Store.StoreMaterial;
import model.enums.AssetType.Material;

import java.io.IOException;
import java.util.ArrayList;

public class PurchaseMenu {
    public ScrollPane scrollPane;
    public HBox Hbox;
    public static MarketMenu mainMarketMenu;
    public static MarketController controller;
    public static String material;
    public Text details;
    public ImageView image;

    public static void setMaterial(String material) {
        PurchaseMenu.material = material;
    }

    public static void setController(MarketController controller) {
        PurchaseMenu.controller = controller;
    }

    public void initialize(){
        StoreMaterial storeMaterial = StoreMaterial.getInstance(material);
        System.out.println(controller);
        int inventory = controller.getCurrentPlayer().getGovernance().getStorageStock(Material.getMaterial(material));
        details.setText(storeMaterial.toString() + "\nstock : " + inventory);
        image.setImage(Material.getMaterial(material).getImage());
    }

    public void back() throws IOException {
        mainMarketMenu.getMain();
    }
}
