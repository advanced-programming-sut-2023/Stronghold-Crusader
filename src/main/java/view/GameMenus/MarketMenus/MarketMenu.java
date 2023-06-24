package view.GameMenus.MarketMenus;

import controller.GameControllers.MarketController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.Menu;
import view.enums.commands.GameCommand.MarketCommand;
import view.enums.messages.GameMessage.MarketMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketMenu {
    public ImageView foodButton, materialButton, weaponButton, pricingListButton;
    public static MarketController marketController;
    private Scanner scanner;
    public AnchorPane scrollPane;
    public AnchorPane mainMenu, pricingMenu;

    public void initialize() throws IOException {
        mainMenu = FXMLLoader.load(
                new URL(MarketMenu.class.getResource("/FXML/Gamefxml/ShopMenusfxml/shopMenuMain.fxml").toExternalForm()));
        scrollPane.getChildren().setAll(mainMenu);
        ShopMainMenu.mainMarketMenu = this;
        PricingMenu.mainMarketMenu = this;
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
}
