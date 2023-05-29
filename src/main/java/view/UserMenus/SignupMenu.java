package view.UserMenus;

import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.SignupAndLoginUtils;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.net.URL;
import java.util.HashMap;

public class SignupMenu extends Application {
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

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirmation;

    private  Popup popup = createPopUp();


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
        HashMap<String,String> inputs = getInputsFromBoxes();
        clearBoxes();
        showSignupResult(signupController.signup(inputs));
    }

    private void showSignupResult(SignupAndLoginMessage signupMessage) {
        setAllErrors("");
        switch (signupMessage){
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
        label.setTranslateX(-480);
        label.setTranslateY(635);
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoHide(true);
        return popup;
    }


    private HashMap<String,String> getInputsFromBoxes() {
        HashMap<String,String> inputs = new HashMap<>();
        inputs.put("username", username.getText());
        inputs.put("password", password.getText());
        inputs.put("passwordConfirmation", passwordConfirmation.getText());
        inputs.put("email", Email.getText());
        inputs.put("slogan", slogan.getText());
        return inputs;
    }

    private void clearBoxes() {
        password.setText("");
        passwordConfirmation.setText("");
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
}



