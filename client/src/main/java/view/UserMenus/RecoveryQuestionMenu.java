package view.UserMenus;

import controller.UserControllers.SignupController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Captcha;
import view.Main;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecoveryQuestionMenu extends Application implements Initializable {
    private final Popup popup = createPopUp();
    private static SignupController controller;
    public TextField recoveryAnswer;
    public Button confirm;
    public Text recoveryError;
    public TextField confirmation;
    public Text confirmationError;
    public TextField captcha;
    public FontAwesomeIcon refresh;
    public ImageView captchaPicture;
    public ChoiceBox<String> questions;
    public Text captchaError;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stronghold");
        AnchorPane anchorPane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/Userfxml/recoveryQuestion.fxml"));
        anchorPane.setBackground(new Background(new BackgroundImage(new Image(
                MainMenu.class.getResource("/assets/backgrounds/loginMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> recoveryQuestions = FXCollections.observableArrayList();
        recoveryQuestions.addAll("What is my father’s name?"
                , "What was my first pet’s name?", "What is my mother’s last name?");
      questions.setItems(recoveryQuestions);
      questions.setValue(recoveryQuestions.get(0));
      generateCaptchaImageview();
      refresh.setOnMousePressed(e -> {
          generateCaptchaImageview();
      });

      confirm.setOnMouseClicked(e -> {
              generateUser(controller.pickQuestion(questions.getValue().toString(), recoveryAnswer.getText(),
                      confirmation.getText(), captcha.getText()));
      });
    }

    private void generateUser(SignupAndLoginMessage signupAndLoginMessage) {
        switch (signupAndLoginMessage) {
            case INVALID_CAPTCHA:
                captchaError.setText("invalid captcha");
                break;
            case FAIL_PICKING_UP_QUESTION:
                recoveryError.setText("answers do not match");
                confirmationError.setText("answers do not match");
                break;
            case SUCCESS_CREATING_USER:
                createPopUp();
                showSuccessMessage();
                goToSignUpMenu();
                break;
        }
        refreshAllFields();
    }

    private void goToSignUpMenu()  {
        new Timeline(new KeyFrame(Duration.seconds(3), e ->{
            popup.hide();
            SignupMenu.setSignupController(new SignupController());
            try {
                new SignupMenu().start(Main.mainStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        })).play();

    }

    private void refreshAllFields() {
        createEraseErrorsTimeLine();
        generateCaptchaImageview();
        recoveryAnswer.setText("");
        confirmation.setText("");
        captcha.setText("");
    }

    private void createEraseErrorsTimeLine(){
        new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            captchaError.setText("");
            confirmationError.setText("");
            recoveryError.setText("");
        })).play();
    }
    private void generateCaptchaImageview() {
        captchaPicture.setImage(Captcha.generateCaptcha());
    }


    private void showSuccessMessage() {
        popup.show(Main.mainStage);
    }



    private Popup createPopUp() {
        Label label = new Label("user create successfully");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.getStylesheets().add(SignupMenu.class.getResource("/Css/style1.css").toString());
        label.getStyleClass().add("pop-up-label");
        //TODO dynamic size
        label.setTranslateX(0);
        label.setTranslateY(635);
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoHide(true);
        return popup;
    }

    public static void setController(SignupController controller) {
        RecoveryQuestionMenu.controller = controller;
    }

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(Main.mainStage);
    }


}
