package view.GameMenus.MarketMenus;

import java.io.IOException;

public class SuccessPopup {
    public void back() throws IOException {
        PurchaseMenu.mainMarketMenu.getMain();
        PurchaseMenu.successPopup.hide();
    }
}
