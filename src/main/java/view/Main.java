package view;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ConstantManager;
import model.Stronghold;
import view.UserMenus.LoginMenu;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        Stronghold.load();
        ConstantManager.load();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.setLoginController(new LoginController());
        new LoginMenu().start(stage);
    }
}
