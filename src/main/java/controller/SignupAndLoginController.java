package controller;

import model.enums.Slogan;
import model.Stronghold;
import model.User;
import utils.FormatValidation;
import utils.Pair;
import utils.SignupAndLoginUtils;
import view.SignupAndLoginMenu;
import view.enums.messages.SignupAndLoginMessages;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class SignupAndLoginController {
    public User currentUser = null;
    private int failedAttempts = 0;
    private LocalDateTime loginTime = null;
    private final Stronghold stronghold = Stronghold.getInstance();
    private MainController mainController;

    private void increaseFailedAttempts() {
        failedAttempts++;
        loginTime = LocalDateTime.now().plus(5 * (long) Math.pow(2, failedAttempts - 1), ChronoUnit.SECONDS);
    }

    private void failedAttemptsReset() {
        failedAttempts = 0;
        loginTime = null;
    }

    public long getTimeUntilLogin() {
        return LocalDateTime.now().until(this.loginTime, ChronoUnit.SECONDS);
    }

    public void run() {
        SignupAndLoginMenu signupAndLoginMenu = new SignupAndLoginMenu(this);
        while (true) {
            switch (signupAndLoginMenu.run()) {
                case "logout":
                    return;
                case "login":
                    mainController.run();
                    break;
            }
        }
    }

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        if (inputs.get("slogan").equals("random"))
            inputs.replace("slogan", inputs.get("slogan"), generateRandomSlogan());
        if (hasEmptyField(inputs))
            return SignupAndLoginMessages.EMPTY_FIELD;
        if (checkFormatOfInputs(inputs) != null)
            return checkFormatOfInputs(inputs);
        if (!inputs.get("password").equals("random") && !inputs.get("password").equals(inputs.get("passwordConfirmation")))
            return SignupAndLoginMessages.CONFIRMATION_ERROR;
        if (stronghold.emailExists(inputs.get("email")))
            return SignupAndLoginMessages.EXISTED_EMAIL;
        if (stronghold.userExists(inputs.get("username")))
            return SignupAndLoginMessages.EXISTING_USERNAME;
        if (inputs.get("password").equals("random")) {
            inputs.replace("password", inputs.get("password"), SignupAndLoginUtils.generateRandomPassword());
            return SignupAndLoginMessages.RANDOM_PASSWORD;
        }
        User newUser = new User(inputs.get("username"), inputs.get("password"), inputs.get("email"),
                inputs.get("nickname"), inputs.get("slogan"));
         stronghold.addUser(newUser);
        mainController = new MainController(newUser);
        return SignupAndLoginMessages.SUCCESS_PROCESS;
    }


    public SignupAndLoginMessages pickQuestion(HashMap<String, String> inputs) {
        int number = Integer.parseInt(inputs.get("questionNumber"));
        if (!inputs.get("answer").equals(inputs.get("answerConfirm")) || (number < 1 || number > 3))
            return SignupAndLoginMessages.FAIL_PICKING_UP_QUESTION;
        Pair pair;
        User user = stronghold.getUser(inputs.get("username"));
        switch (number) {
            case 1:
                pair = new Pair("What is my father’s name?", inputs.get("answer"));
                break;
            case 2:
                pair = new Pair("What was my first pet’s name?", inputs.get("answer"));
                break;
            case 3:
                pair = new Pair("What is my mother’s last name?", inputs.get("answer"));
                break;
            default:
                pair = new Pair("", "");
                break;
        }
        user.setPasswordRecovery(pair);
        return SignupAndLoginMessages.SUCCESS_CREATING_USER;
    }

    public SignupAndLoginMessages login(HashMap<String, String> inputs) {
        if (inputs.get("username") == null || inputs.get("password") == null)
            return SignupAndLoginMessages.EMPTY_FIELD;
        if (Stronghold.getInstance().getUser(inputs.get("username")) == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        if (this.loginTime != null && LocalDateTime.now().isBefore(loginTime))
            return SignupAndLoginMessages.TOO_MANY_ATTEMPTS;
        if (!Stronghold.getInstance().getUser(inputs.get("username")).isPasswordCorrect(inputs.get("password"))) {
            increaseFailedAttempts();
            return SignupAndLoginMessages.INCORRECT_PASSWORD;
        }
        failedAttemptsReset();
        return SignupAndLoginMessages.SUCCESS_PROCESS;
    }

    public SignupAndLoginMessages getCurrentUser(String username) {
        currentUser = stronghold.getUser(username);
        if (currentUser == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        return SignupAndLoginMessages.SUCCESS_PROCESS;
    }

    private boolean hasEmptyField(HashMap<String, String> inputs) {
        return inputs.get("password") == null || inputs.get("email") == null
                || inputs.get("email") == null || inputs.get("slogan").equals("");
    }

    private SignupAndLoginMessages checkFormatOfInputs(HashMap<String, String> inputs) {
        if (!FormatValidation.isFormatValid(inputs.get("username"), FormatValidation.USERNAME))
            return SignupAndLoginMessages.INVALID_USERNAME_FORMAT;

        if (!inputs.get("password").equals("random")) {
            if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LENGTH))
                return SignupAndLoginMessages.PASSWORD_WEEK_LENGTH;
            if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LETTERS))
                return SignupAndLoginMessages.PASSWORD_WEEK_LETTERS_PROBLEM;
        }

        if (!FormatValidation.isFormatValid(inputs.get("email"), FormatValidation.EMAIL))
            return SignupAndLoginMessages.INVALID_EMAIL_FORMAT;
        return null;
    }

    private String generateRandomSlogan() {
        return Slogan.values()[(int) (Math.random() * 5)].getSlogan();
    }

}
