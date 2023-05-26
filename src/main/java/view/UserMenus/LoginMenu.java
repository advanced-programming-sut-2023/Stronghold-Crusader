package view.UserMenus;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;


public class LoginMenu extends Application implements Initializable {
    private LoginController loginController;
    public static Stage mainStage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("FXML/loginMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginController = new LoginController();
    }


}
