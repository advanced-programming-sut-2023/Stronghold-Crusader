package view.UserMenus;

import controller.UserControllers.LoginController;
import controller.UserControllers.ProfileController;
import controller.UserControllers.SignupController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ResourceBundle;


public class LoginMenu extends Application implements Initializable {
    private static LoginController loginController;
    public static Stage stage;
    public Text passwordError;
    public Text userError;
    public Button loginButton;
    public Text attemptsError;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private final ToggleSwitch toggleSwitch = new ToggleSwitch(25, Color.TRANSPARENT);
    ;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("/FXML/loginMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        toggleSwitch.setTranslateX(660);
        toggleSwitch.setTranslateY(405);
        anchorPane.getChildren().add(toggleSwitch);
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }


    private void HandleKeys() {
        LoginMenu.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    try {
                        login();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void goToSignUpMenu(MouseEvent mouseEvent) throws Exception {
        SignupMenu.setSignupController(new SignupController());
        new SignupMenu().start(LoginMenu.stage);
    }

    public static void setLoginController(LoginController loginController) {
        LoginMenu.loginController = loginController;
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        //TODO fix place
        HandleKeys();
        HashMap<String, String> inputs = getInputsFromBoxes();
        showLoginResult(loginController.login(inputs));
    }

    public void login() throws Exception {
        HashMap<String, String> inputs = getInputsFromBoxes();
        showLoginResult(loginController.login(inputs));
    }

    private HashMap<String, String> getInputsFromBoxes() {
        HashMap<String, String> inputs = new HashMap<>();
        inputs.put("username", username.getText());
        inputs.put("password", password.getText());
        username.setText("");
        password.setText("");
        return inputs;
    }

    private void showLoginResult(SignupAndLoginMessage loginMessage) throws Exception {
        switch (loginMessage) {
            case EMPTY_FIELD:
                userError.setText(loginMessage.getOutput());
                passwordError.setText(loginMessage.getOutput());
                attemptsError.setText("");
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
                break;
            case SUCCESS_PROCESS:
                goToMainMenu(loginController.currentUser);
                break;
        }
    }

    private void setNewThreadForCountingLoginTime() {
        Thread timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    if ((loginController.getLoginTime() != null) && LocalDateTime.now().isBefore(loginController.getLoginTime())) {
                        int minutes = (int) (loginController.getTimeUntilLogin() / 60);
                        int seconds = (int) (loginController.getTimeUntilLogin() % 60);
                        attemptsError.setText("Too many failed attempts. Please wait " + minutes + " minutes and " +
                                seconds + " seconds before trying again");
                    } else
                        attemptsError.setText("");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        timeThread.setDaemon(true);
        timeThread.start();
    }

    private void convertLoginTime() {
        int minutes = (int) (loginController.getTimeUntilLogin() / 60);
        int seconds = (int) (loginController.getTimeUntilLogin() % 60);
        attemptsError.setText("Too many failed attempts. Please wait " + minutes + " minutes and " +
                seconds + " seconds before trying again");
    }

    private void goToMainMenu(User user) throws Exception {
        if (toggleSwitch.getSwitchedOnProperty())
            UserManager.setLoggedInUser(user);
        ProfileMenu.setProfileController(new ProfileController(user));
        new ProfileMenu().start(LoginMenu.stage);
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNewThreadForCountingLoginTime();
    }
}
