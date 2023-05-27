package view;

import javafx.stage.Stage;
import model.ConstantManager;
import model.Stronghold;
import view.UserMenus.LoginMenu;

public class Main {
    public static void main(String[] args) throws Exception {
        Stronghold.load();
        ConstantManager.load();

        new LoginMenu().start(new Stage());
    }
}
