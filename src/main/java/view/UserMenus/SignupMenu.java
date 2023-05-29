package view.UserMenus;

import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
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

    private final Popup popup = createPopUp();


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("/FXML/signupMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        anchorPane.getChildren().get(0).setVisible(false);
        anchorPane.getChildren().get(2).setVisible(false);
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        password.textProperty().addListener((observable, oldText, newText) -> {
            if (visiblePassword.isVisible()) password.setText(visiblePassword.getText());
            passwordError.setText(FormatValidation.isPasswordValid(password.getText()).getOutput());
        });
    }

    public static void setSignupController(SignupController signupController) {
        SignupMenu.signupController = signupController;
    }

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);

    }

    public void generateRandomPassword(MouseEvent mouseEvent) {
        visiblePassword.setText(SignupAndLoginUtils.generateRandomPassword());
        if (password.isVisible()) changePasswordVisibility();
    }

    public void generateRandomSlogan(MouseEvent mouseEvent) {
        slogan.setText(signupController.generateRandomSlogan());
    }

    public void signup(MouseEvent mouseEvent) {
        HashMap<String, String> inputs = getInputsFromBoxes();
        showSignupResult(signupController.signup(inputs));
    }

    private void showSignupResult(SignupAndLoginMessage signupMessage) {
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
                showSuccessMessage();
                break;
        }
    }

    private void showSuccessMessage() {
        popup.show(LoginMenu.stage);
    }

    private Popup createPopUp() {
        Label label = new Label("user create successfully");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.getStylesheets().add(SignupMenu.class.getResource("/Css/style1.css").toString());
        label.getStyleClass().add("pop-up-label");
        //TODO dynamic size
        label.setTranslateX(-480);
        label.setTranslateY(635);
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoHide(true);
        return popup;
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



