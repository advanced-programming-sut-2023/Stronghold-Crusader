package view.GameMenus.MarketMenus;

import controller.GameControllers.MarketController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Game.Store.StoreMaterial;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MarketMenu {
    public ImageView foodButton, materialButton, weaponButton, pricingListButton;
    public static MarketController marketController;
    private Scanner scanner;
    public AnchorPane scrollPane;
    public AnchorPane mainMenu, pricingMenu, listMenu, purchaseMenu;

    public void initialize() throws IOException {
        MarketMainMenu.mainMarketMenu = this;
        PricingMenu.mainMarketMenu = this;
        mainMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/shopMenuMain.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(mainMenu);
    }

    public static void setMarketController(MarketController controller) {
        marketController = controller;
    }

    public void getPricing() throws IOException {
        pricingMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/pricingMenu.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(pricingMenu);
    }

    public void getMain() throws IOException {
        mainMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/shopMenuMain.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(mainMenu);
    }

    public void getListMenu(String type) throws IOException {
        switch (type){
            case "food":
                MaterialListMenu.setMaterials(StoreMaterial.getFoods());
                break;
            case "material":
                MaterialListMenu.setMaterials(StoreMaterial.getMaterials());
                break;
            case "weapon":
                MaterialListMenu.setMaterials(StoreMaterial.getWeapons());
                break;
        }
        MaterialListMenu.mainMarketMenu = this;
        listMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/materialListMenu.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(listMenu);
    }

    public void getPurchaseMenu(String material) throws IOException {
        PurchaseMenu.setMaterial(material);
        PurchaseMenu.mainMarketMenu = this;
        purchaseMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/purchaseMenu.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(purchaseMenu);
    }
}
