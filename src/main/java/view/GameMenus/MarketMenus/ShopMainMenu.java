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
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMainMenu {
    public ImageView foodButton, materialButton, weaponButton, pricingListButton;
    private MarketController marketController;
    public static MarketMenu mainMarketMenu;
    public void getPricing() throws IOException {
        mainMarketMenu.getPricing();
    }

    public void buyFood(){

    }

    public void buyWeapon(){

    }
    public void buyMaterial(){

    }
}
