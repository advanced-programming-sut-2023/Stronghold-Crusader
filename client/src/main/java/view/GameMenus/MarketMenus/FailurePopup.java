package view.GameMenus.MarketMenus;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;

public class FailurePopup {

    public Text error;
    private static String errorText;

    public static void setErrorText(String errorText) {
        FailurePopup.errorText = errorText;
    }

    public void initialize(){
        error.setText(errorText);
    }

    public void back() throws IOException {
        PurchaseMenu.mainMarketMenu.getMain();
        PurchaseMenu.failurePopup.hide();
    }
}
