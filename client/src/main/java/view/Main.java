package view;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ConstantManager;
import network.Connection;
import view.UserMenus.LoginMenu;

import java.io.IOException;

public class Main extends Application {
    public static Stage mainStage;

    public static void main(String[] args) throws Exception {
        Connection.connect("localhost", 8080);
        ConstantManager.load();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.setLoginController(new LoginController());
        mainStage = stage;
        stage.setOnCloseRequest(event -> {
            try {
                Connection.getInstance().closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        new LoginMenu().start(stage);
    }
}
