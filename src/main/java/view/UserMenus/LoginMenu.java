package view.UserMenus;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User.User;
import model.User.UserManager;
import utils.ToggleSwitch;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.net.URL;
import java.util.HashMap;


public class LoginMenu extends Application {
    private static LoginController loginController;
    public static Stage stage;
    public Text passwordError;
    public Text userError;
    public Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private ToggleSwitch toggleSwitch;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("/FXML/loginMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        toggleSwitch = new ToggleSwitch(25, Color.TRANSPARENT);
        toggleSwitch.setTranslateX(660);
        toggleSwitch.setTranslateY(405);
        anchorPane.getChildren().add(toggleSwitch);
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        loginController = new LoginController();
        stage.show();
    }

    private void HandleKeys() {
     LoginMenu.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent keyEvent) {
             if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                 login();
             }
         }
     });
    }

    public void goToSignUpMenu(MouseEvent mouseEvent) throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }

    public static void setLoginController(LoginController loginController) {
        LoginMenu.loginController = loginController;
    }

    public void login(MouseEvent mouseEvent) {
        //TODO fix place
        HandleKeys();
        HashMap<String, String> inputs = getInputsFromBoxes();
        showLoginResult(loginController.login(inputs));
    }
    public void login() {
        HashMap<String, String> inputs = getInputsFromBoxes();
        showLoginResult(loginController.login(inputs));
    }
    private HashMap<String,String> getInputsFromBoxes(){
        HashMap<String,String> inputs = new HashMap<>();
        inputs.put("username",username.getText());
        inputs.put("password",password.getText());
        username.setText("");
        password.setText("");
        return inputs;
    }

    private void showLoginResult(SignupAndLoginMessage loginMessage) {
        switch (loginMessage){
            case EMPTY_FIELD:
                userError.setText(loginMessage.getOutput());
                passwordError.setText(loginMessage.getOutput());
                break;
            case USER_DOES_NOT_EXIST:
                userError.setText(loginMessage.getOutput());
                passwordError.setText("");
                break;
            case TOO_MANY_ATTEMPTS:
                userError.setText("");
                passwordError.setText("");
                break;
            case INCORRECT_PASSWORD:
                passwordError.setText(loginMessage.getOutput());
                userError.setText("");
            case SUCCESS_PROCESS:
                goToMainMenu(loginController.currentUser);
        }
    }

    private void goToMainMenu(User user) {
        if (toggleSwitch.switchedOnProperty().equals(true))
            UserManager.setLoggedInUser(user);

    }
}
