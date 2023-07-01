package view.UserMenus;

import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.FormatValidation;
import utils.MenusUtils;
import view.Main;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SignupMenu extends Application implements Initializable {
    private static SignupController signupController;
    public TextField Email;
    public CheckBox sloganShow;

    public TextField slogan;
    public ImageView flagIcon;
    public Button randomSloganButton;
    public Text usernameError;
    public Text passwordError;
    public Text passwordConfirmationError;
    public Text emailError;
    public Text entireError;
    public ImageView eye;
    public TextField visiblePassword;
    public ImageView closeEye;
    public TextField nickname;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirmation;



    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stronghold");
        AnchorPane anchorPane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/Userfxml/signupMenu.fxml"));

        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/loginMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeEye.setVisible(false);
        visiblePassword.setVisible(false);
        password.textProperty().addListener((observable, oldText, newText) -> {
            if (visiblePassword.isVisible()) password.setText(visiblePassword.getText());
            passwordError.setText(FormatValidation.isPasswordValid(password.getText()).getOutput());
        });

        Email.textProperty().addListener((observable, oldText, newText) -> {
            if (!FormatValidation.isFormatValid(Email.getText(), FormatValidation.EMAIL))
                emailError.setText(SignupAndLoginMessage.INVALID_EMAIL_FORMAT.getOutput());
            else emailError.setText("");
        });

        username.textProperty().addListener((observable, oldText, newText) -> {
            if (!FormatValidation.isFormatValid(username.getText(), FormatValidation.USERNAME)
            && !username.getText().equals(""))
                usernameError.setText(SignupAndLoginMessage.INVALID_USERNAME_FORMAT.getOutput());
            else usernameError.setText("");
        });
    }


    public static void setSignupController(SignupController signupController) {
        SignupMenu.signupController = signupController;
    }

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(Main.mainStage);

    }

    public void generateRandomPassword(MouseEvent mouseEvent) {
        visiblePassword.setText(MenusUtils.generateRandomPassword());
        if (password.isVisible()) changePasswordVisibility();
    }

    public void generateRandomSlogan(MouseEvent mouseEvent) {
        slogan.setText(signupController.generateRandomSlogan());
    }

    public void signup(MouseEvent mouseEvent) throws Exception {
        HashMap<String, String> inputs = getInputsFromBoxes();
        showSignupResult(signupController.signup(inputs));
    }

    private void showSignupResult(SignupAndLoginMessage signupMessage) throws Exception {
        password.setText("");
        passwordConfirmation.setText("");
        visiblePassword.setText("");
        switch (signupMessage) {
            case EMPTY_FIELD:
                entireError.setText(signupMessage.getOutput());
                break;
            case EXISTING_USERNAME:
                usernameError.setText(signupMessage.getOutput());
                break;
            case EXISTED_EMAIL:
                emailError.setText(signupMessage.getOutput());
                break;
            case CONFIRMATION_ERROR:
                passwordError.setText(signupMessage.getOutput());
                passwordConfirmationError.setText(signupMessage.getOutput());
                break;
            case SUCCESS_PROCESS:
                clearBoxes();
                RecoveryQuestionMenu.setController(signupController);
                new RecoveryQuestionMenu().start(Main.mainStage);
                break;
        }
    }





    private HashMap<String, String> getInputsFromBoxes() {
        HashMap<String, String> inputs = new HashMap<>();
        inputs.put("username", username.getText());
        if (password.isVisible())
            inputs.put("password", password.getText());
        else inputs.put("password", visiblePassword.getText());
        inputs.put("passwordConfirmation", passwordConfirmation.getText());
        inputs.put("email", Email.getText());
        inputs.put("slogan", slogan.getText());
        inputs.put("nickname", nickname.getText());
        return inputs;
    }

    private void clearBoxes() {
        password.setText("");
        passwordConfirmation.setText("");
        Email.setText("");
        nickname.setText("");
        slogan.setText("");
        username.setText("");
    }

    public void changeExistenceOfSlogan(MouseEvent mouseEvent) {
        if (!sloganShow.isSelected()) {
            slogan.setText("");
            slogan.setDisable(true);
        } else slogan.setDisable(false);
    }

    private void setAllErrors(String string) {
        usernameError.setText(string);
        passwordError.setText(string);
        passwordConfirmationError.setText(string);
        emailError.setText(string);
        entireError.setText(string);

    }

    public void changePasswordVisibility(MouseEvent mouseEvent) {
        if (!visiblePassword.isVisible()) {
            visiblePassword.setText(password.getText());
        } else password.setText(visiblePassword.getText());
        password.setVisible(!password.isVisible());
        visiblePassword.setVisible(!visiblePassword.isVisible());
        eye.setVisible(password.isVisible());
        closeEye.setVisible(visiblePassword.isVisible());
    }

    public void changePasswordVisibility() {
        password.setText("");
        password.setVisible(!password.isVisible());
        visiblePassword.setVisible(!visiblePassword.isVisible());
        eye.setVisible(password.isVisible());
        closeEye.setVisible(visiblePassword.isVisible());
    }


}



