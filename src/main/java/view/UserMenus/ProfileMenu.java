package view.UserMenus;

import controller.UserControllers.AvatarController;
import controller.UserControllers.ProfileController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Captcha;
import utils.FormatValidation;
import view.enums.messages.UserMessage.ProfileMessage;

public class ProfileMenu extends Application {
    private static Stage stage;
    private static ProfileController controller;
    public TextField usernameTextField;
    public TextField nicknameTextField;
    public TextField emailTextField;
    public Button usernameChangeButton;
    public Button nicknameChangeButton;
    public Button emailChangeButton;
    public Label usernameError;
    public Label nicknameError;
    public Label emailError;
    public AnchorPane containingPane;
    public AnchorPane popUpPane;
    public Label popUpLabel;
    public AnchorPane passwordPane;
    public PasswordField oldPasswordField;
    public PasswordField newPasswordField;
    public TextField captchaField;
    public Label newPasswordError;
    public ImageView captchaImageView;
    public TextField sloganTextField;
    public Button sloganChangeButton;
    public Label sloganError;
    public ImageView avatarDisplay;

    public static void setProfileController(ProfileController profileController) {
        ProfileMenu.controller = profileController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane rootPane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/Userfxml/profileMenu.fxml"));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.setScene(new Scene(rootPane));
        ProfileMenu.stage = stage;
        stage.setFullScreen(true);
    }

    @FXML
    private void initialize() {
        containingPane.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, new CornerRadii(30), null)));
        popUpPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(20), null)));
        passwordPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), null)));
        usernameTextField.setText(controller.getCurrentUser().getUsername());
        emailTextField.setText(controller.getCurrentUser().getEmail());
        nicknameTextField.setText(controller.getCurrentUser().getNickname());
        sloganTextField.setText(controller.getCurrentUser().getSlogan());
        updateAvatarDisplay();
        addActionEvents();
        initializeFieldListeners();
    }

    private void addActionEvents() {
        usernameChangeButton.setOnAction(actionEvent -> usernameChangeClicked());
        emailChangeButton.setOnAction(actionEvent -> emailChangeClicked());
        nicknameChangeButton.setOnAction(actionEvent -> nicknameChangeClicked());
        sloganChangeButton.setOnAction(actionEvent -> sloganChangeClicked());
    }

    private void initializeFieldListeners() {
        newPasswordField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!FormatValidation.isFormatValid(newValue, FormatValidation.PASSWORD_LENGTH))
                newPasswordError.setText(ProfileMessage.INVALID_PASSWORD_LENGTH.getMessage());
            else if (!FormatValidation.isFormatValid(newValue, FormatValidation.PASSWORD_LETTERS_NUMBER))
                newPasswordError.setText(ProfileMessage.INVALID_PASSWORD_FORMAT.getMessage());
            else
                newPasswordError.setText("");
        });
        usernameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!FormatValidation.isFormatValid(newValue, FormatValidation.USERNAME))
                usernameError.setText(ProfileMessage.INVALID_USERNAME_FORMAT.getMessage());
            else
                usernameError.setText("");
        });
        emailTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!FormatValidation.isFormatValid(newValue, FormatValidation.EMAIL))
                emailError.setText(ProfileMessage.INVALID_EMAIL_FORMAT.getMessage());
            else
                emailError.setText("");
        });
    }

    private void updateAvatarDisplay() {
        avatarDisplay.setImage(new Image(controller.getCurrentUser().getAvatarPath()));
    }

    private void sloganChangeClicked() {
        if (sloganTextField.isDisabled()) {
            sloganTextField.setDisable(false);
            sloganChangeButton.setText("Confirm");
        } else {
            if (!sloganError.getText().isEmpty()) return;
            ProfileMessage resultMessage = controller.changeSlogan(sloganTextField.getText());
            showPopUp(resultMessage.getMessage());
            new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> hidePopUp())).play();
            if (resultMessage.equals(ProfileMessage.SLOGAN_CHANGE_SUCCESS)) {
                sloganTextField.setDisable(true);
                sloganChangeButton.setText("Change");
            }
        }
    }

    private void usernameChangeClicked() {
        if (usernameTextField.isDisabled()) {
            usernameTextField.setDisable(false);
            usernameChangeButton.setText("Confirm");
        } else {
            if (!usernameError.getText().isEmpty()) return;
            ProfileMessage resultMessage = controller.changeUsername(usernameTextField.getText());
            showPopUp(resultMessage.getMessage());
            new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> hidePopUp())).play();
            if (resultMessage.equals(ProfileMessage.USERNAME_CHANGE_SUCCESS)) {
                usernameTextField.setDisable(true);
                usernameChangeButton.setText("Change");
            }
        }
    }

    private void nicknameChangeClicked() {
        if (nicknameTextField.isDisabled()) {
            nicknameTextField.setDisable(false);
            nicknameChangeButton.setText("Confirm");
        } else {
            if (!nicknameError.getText().isEmpty()) return;
            ProfileMessage resultMessage = controller.changeNickname(nicknameTextField.getText());
            showPopUp(resultMessage.getMessage());
            new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> hidePopUp())).play();
            if (resultMessage.equals(ProfileMessage.NICKNAME_CHANGE_SUCCESS)) {
                nicknameTextField.setDisable(true);
                nicknameChangeButton.setText("Change");
            }
        }
    }

    private void emailChangeClicked() {
        if (emailTextField.isDisabled()) {
            emailTextField.setDisable(false);
            emailChangeButton.setText("Confirm");
        } else {
            if (!emailError.getText().isEmpty()) return;
            ProfileMessage resultMessage = controller.changeEmail(emailTextField.getText());
            showPopUp(resultMessage.getMessage());
            new Timeline(new KeyFrame(Duration.seconds(2), actionEvent1 -> hidePopUp())).play();
            if (resultMessage.equals(ProfileMessage.USERNAME_CHANGE_SUCCESS)) {
                emailTextField.setDisable(true);
                emailChangeButton.setText("Change");
            }
        }
    }

    public void changePasswordClicked() {
        passwordPane.setVisible(true);
        oldPasswordField.requestFocus();
        captchaImageView.setImage(Captcha.generateCaptcha());
    }

    public void confirmPasswordClicked() {
        if (!newPasswordError.getText().isEmpty()) return;
        ProfileMessage resultMessage = controller.changePassword(newPasswordField.getText(),
                oldPasswordField.getText(), captchaField.getText());
        showPopUp(resultMessage.getMessage());
        new Timeline(new KeyFrame(Duration.seconds(2), actionEvent1 -> hidePopUp())).play();
        if (resultMessage.equals(ProfileMessage.PASSWORD_CHANGE_SUCCESS)) hidePasswordPane();
    }

    public void hidePasswordPane() {
        passwordPane.setVisible(false);
        oldPasswordField.clear();
        newPasswordField.clear();
        captchaField.clear();
        newPasswordError.setText("");
    }

    public void sloganClear() {
        sloganTextField.clear();
        ProfileMessage resultMessage = controller.removeSlogan();
        showPopUp(resultMessage.getMessage());
        new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> hidePopUp())).play();
    }

    private void showPopUp(String text) {
        popUpPane.setVisible(true);
        popUpLabel.setText(text);
    }

    private void hidePopUp() {
        popUpPane.setVisible(false);
        popUpLabel.setText("");
    }

    public void openAvatarMenu() throws Exception {
        AvatarMenu.setController(new AvatarController(controller.getCurrentUser()));
        new AvatarMenu().start(stage);
    }

    public void back() {
        //TODO add back to main menu
    }
}
