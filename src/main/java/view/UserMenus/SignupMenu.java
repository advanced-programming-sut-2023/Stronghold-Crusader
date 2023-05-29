package view.UserMenus;

import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.SignupAndLoginUtils;

import java.net.URL;

public class SignupMenu extends Application {
    private static SignupController signupController;
    public TextField Email;
    public CheckBox sloganShow;

    public TextField slogan;
    public ImageView flagIcon;
    public Button randomSloganButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirmation;



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

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);

    }

    public void generateRandomPassword(MouseEvent mouseEvent) {
        password.setPromptText(SignupAndLoginUtils.generateRandomPassword());
    }

    public void generateRandomSlogan(MouseEvent mouseEvent) {
        slogan.setText(signupController.generateRandomSlogan());
    }

    public void signup(MouseEvent mouseEvent) {
    }

    public void changeExistenceOfSlogan(MouseEvent mouseEvent) {
        setSloganVisibilityProperty(sloganShow.isSelected());
        if (!sloganShow.isSelected())
            slogan.setText("");
        
    }

    private void setSloganVisibilityProperty(boolean b) {
        slogan.setVisible(b);
        flagIcon.setVisible(b);
        randomSloganButton.setVisible(b);
    }
}



