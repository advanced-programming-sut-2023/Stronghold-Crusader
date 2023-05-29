package view.UserMenus;

import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;

public class SignupMenu extends Application {
    private static SignupController signupController;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("/FXML/signupMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public static void setSignupController(SignupController signupController) {
        SignupMenu.signupController = signupController;
    }
}



