package view.UserMenus;

import controller.UserControllers.LoginController;
import controller.UserControllers.MainController;
import controller.UserControllers.SignupController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Stronghold;
import model.User.User;
import model.User.UserManager;
import network.Connection;
import network.Request;
import utils.ToggleSwitch;
import view.Main;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ResourceBundle;


public class LoginMenu extends Application implements Initializable {
    private static LoginController loginController;
    public Text passwordError;
    public Text userError;
    public Button loginButton;
    public Text attemptsError;
    public Pane mainPane;
    public TextField answer;
    public Text recoveryQuestion;
    public TextField newPassword;
    public Text recoveryError;


    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private final ToggleSwitch toggleSwitch = new ToggleSwitch(25, Color.TRANSPARENT);
    private Thread timeThread;


    @Override
    public void start(Stage stage) throws Exception {
        if (UserManager.getLoggedInUser() != null) {
            User toBeLoggedIn = Stronghold.getInstance().getUser(UserManager.getLoggedInUser().getUsername());
            UserManager.setLoggedInUser(toBeLoggedIn);
            Request request = new Request();
            request.setType("connect");
            request.setCommand("login");
            request.addParameter("username", UserManager.getLoggedInUser().getUsername());
            String response = Connection.getInstance().sendRequest(request);
            if(!response.equals("400: Already logged in"))
                goToMainMenu(toBeLoggedIn);
            return;
        }
        stage.setTitle("Stronghold");
        URL url = LoginMenu.class.getResource("/FXML/Userfxml/loginMenu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/loginMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        setToggleSwitch();
        Pane pane = (Pane) anchorPane.getChildren().get(5);
        pane.getChildren().add(toggleSwitch);
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    private void setToggleSwitch() {
        toggleSwitch.setTranslateX(157);
        toggleSwitch.setTranslateY(188);
        toggleSwitch.setOnMouseClicked(event -> {
            toggleSwitch.setSwitchedOn(!toggleSwitch.getSwitchedOnProperty());
            loginController.loggedInProperty = !loginController.loggedInProperty;
        });
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeThread = setNewThreadForCountingLoginTime();
        timeThread.start();
    }


    private void HandleKeys() {
        Main.mainStage.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    login();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void goToSignUpMenu(MouseEvent mouseEvent) throws Exception {
        SignupMenu.setSignupController(new SignupController());
        new SignupMenu().start(Main.mainStage);
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
            case ALREADY_LOGGED_IN:
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

    private Thread setNewThreadForCountingLoginTime() {
        Thread timeThread = new Thread(() -> {
            while (true) {
                if (attemptsError == null) break;
                if ((loginController.getLoginTime() != null) && LocalDateTime.now().isBefore(loginController.getLoginTime())) {
                    convertLoginTime();
                } else
                    attemptsError.setText("");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timeThread.setDaemon(true);
        return timeThread;
    }

    private void convertLoginTime() {
        int minutes = (int) (loginController.getTimeUntilLogin() / 60);
        int seconds = (int) (loginController.getTimeUntilLogin() % 60);
        attemptsError.setText("Too many failed attempts. Please wait " + minutes + " minutes and " +
                seconds + " seconds before trying again");
    }

    private void goToMainMenu(User user) throws Exception {
        if (loginController.loggedInProperty)
            UserManager.setLoggedInUser(user);
        MainMenu menu = new MainMenu();
        MainMenu.setMainController(new MainController(user, menu));
        menu.start(Main.mainStage);
    }

    public void goToChangePasswordPane(MouseEvent mouseEvent) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(LoginMenu.class.getResource("/FXML/Userfxml/forgotPasswordPane.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(fxml);
        initializePane();
    }

    private void initializePane() {
        Pane pane = (Pane) mainPane.getChildren().get(0);
        pane.getChildren().get(0).setVisible(false);
        pane.getChildren().get(6).setVisible(false);
        pane.getChildren().get(7).setVisible(false);
        pane.getChildren().get(8).setVisible(false);
    }

    public void changePassword(MouseEvent mouseEvent) {
        SignupAndLoginMessage checkUser = loginController.checkUserExist(username.getText());
        switch (checkUser) {
            case USER_DOES_NOT_EXIST:
                userError.setText(checkUser.getOutput());
                break;
            case SUCCESS_PROCESS:
                visibleItems();
                userError.setText("");
                recoveryQuestion.setText(loginController.currentUser.getPasswordRecoveryQuestion());
                break;

        }
        username.setText("");
    }

    private void visibleItems() {
        mainPane.getChildren().get(0).setVisible(true);
        mainPane.getChildren().get(6).setVisible(true);
        mainPane.getChildren().get(7).setVisible(true);
        mainPane.getChildren().get(8).setVisible(true);
    }

    public void backToLogin(MouseEvent mouseEvent) throws Exception {
        LoginMenu.setLoginController(new LoginController());
        new LoginMenu().start(Main.mainStage);
    }

    public void checkPasswordValidation(MouseEvent mouseEvent) {
        switch (loginController.changePassword(answer.getText(), newPassword.getText())) {
            case INCORECT_RECOVERY_ANSWER:
                recoveryError.setText("your recovery answer is incorrect");
                break;
            case WEEK_SIMPLE_PASSWORD:
                recoveryError.setText("your password is week");
                break;
            case SUCCESS_PROCESS:
                createPopUp().show(Main.mainStage);
                break;
        }
        answer.setText("");
        newPassword.setText("");
        new Timeline(new KeyFrame(Duration.seconds(3), e -> recoveryError.setText(""))).play();

    }

    private Popup createPopUp() {
        Label label = new Label("user create successfully");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.getStylesheets().add(SignupMenu.class.getResource("/Css/style1.css").toString());
        label.getStyleClass().add("pop-up-label");
        //TODO dynamic size
        label.setTranslateX(-150);
        label.setTranslateY(635);
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoHide(true);
        new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            popup.hide();
            LoginMenu.setLoginController(new LoginController());
            try {
                new LoginMenu().start(Main.mainStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        })).play();
        return popup;

    }
}
