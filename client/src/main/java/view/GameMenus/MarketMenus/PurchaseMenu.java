package view.GameMenus.MarketMenus;

import controller.GameControllers.MarketController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import model.Game.Store.StoreMaterial;
import model.enums.AssetType.Material;
import view.GameMenus.GameMenu;
import view.Main;
import view.enums.messages.GameMessage.MarketMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PurchaseMenu {
    public ScrollPane scrollPane;
    public HBox Hbox;
    public static MarketMenu mainMarketMenu;
    public static MarketController controller;
    public static String material;
    public static Popup successPopup, failurePopup;
    public Text details;
    public ImageView image;

    public static void setMaterial(String material) {
        PurchaseMenu.material = material;
    }

    public static void setController(MarketController controller) {
        PurchaseMenu.controller = controller;
    }

    public void initialize() {
        StoreMaterial storeMaterial = StoreMaterial.getInstance(material);
        int inventory = controller.getCurrentPlayer().getGovernance().getStorageStock(Material.getMaterial(material));
        details.setText(storeMaterial.toString() + "\nstock : " + inventory);
        image.setImage(Material.getMaterial(material).getImage());
    }

    public void back() throws IOException {
        mainMarketMenu.getMain();
    }

    public void buy() throws IOException {
        MarketMessage msg = controller.buy(material, 1);
        if (msg.equals(MarketMessage.BUY_SUCCESS)) {
            successPopup = new Popup();
            AnchorPane pane = FXMLLoader.load(
                    new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/successPopup.fxml").toExternalForm()));
            successPopup.getContent().add(pane);
            successPopup.show(Main.mainStage);
        } else {
            failurePopup = new Popup();
            FailurePopup.setErrorText(msg.getMessage());
            AnchorPane pane = FXMLLoader.load(
                    new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/failurePopup.fxml").toExternalForm()));
            failurePopup.getContent().add(pane);
            failurePopup.show(Main.mainStage);
        }
    }

    public void sell() throws IOException {
        MarketMessage msg = controller.sell(material, 1);
        if (msg.equals(MarketMessage.BUY_SUCCESS)) {
            successPopup = new Popup();
            AnchorPane pane = FXMLLoader.load(
                    new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/successPopup.fxml").toExternalForm()));
            successPopup.getContent().add(pane);
            successPopup.show(Main.mainStage);
        } else {
            failurePopup = new Popup();
            FailurePopup.setErrorText(msg.getMessage());
            AnchorPane pane = FXMLLoader.load(
                    new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/failurePopup.fxml").toExternalForm()));
            failurePopup.getContent().add(pane);
            failurePopup.show(Main.mainStage);
        }
    }
}
