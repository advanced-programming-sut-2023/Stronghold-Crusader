package controller.GameControllers;

import model.User.User;
import view.GameMenus.TradeMenu;

public class TradeController {
    private User currentUser;

    public TradeController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        TradeMenu menu = new TradeMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }
}
