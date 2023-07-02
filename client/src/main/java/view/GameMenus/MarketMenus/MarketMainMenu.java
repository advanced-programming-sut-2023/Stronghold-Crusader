package view.GameMenus.MarketMenus;

import javafx.scene.image.ImageView;

import java.io.IOException;

public class MarketMainMenu {
    public ImageView foodButton, materialButton, weaponButton, pricingListButton;
    public static MarketMenu mainMarketMenu;
    public void getPricing() throws IOException {
        mainMarketMenu.getPricing();
    }

    public void buyFood() throws IOException {
        mainMarketMenu.getListMenu("food");
    }

    public void buyMaterial() throws IOException {
        mainMarketMenu.getListMenu("material");
    }
    public void buyWeapon() throws IOException {
        mainMarketMenu.getListMenu("weapon");
    }

}
