package controller;

import model.Slogan;
import model.Stronghold;
import model.User;
import utils.FormatValidation;
import utils.Pair;
import utils.SignupAndLoginUtils;
import view.enums.messages.SignupAndLoginMessages;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class SignupAndLoginController {
    public User currentUser = null;
    private int failedAttempts = 0;
    private LocalDateTime loginTime = null;
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

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        if (inputs.get("slogan").equals("random"))
            inputs.replace("slogan", inputs.get("slogan"), Slogan.values()[(int) (Math.random() * 5)].getSlogan());
        if (inputs.get("password") == null || inputs.get("email") == null
                || inputs.get("email") == null || inputs.get("slogan").equals(""))
            return SignupAndLoginMessages.EMPTY_FIELD;
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
        if (!inputs.get("password").equals("random") && !inputs.get("password").equals(inputs.get("passwordConfirmation")))
            return SignupAndLoginMessages.CONFIRMATION_ERROR;
        if (Stronghold.isEmailExist(inputs.get("email")))
            return SignupAndLoginMessages.EXISTED_EMAIL;
        if (Stronghold.doesUserExist(inputs.get("username")))
            return SignupAndLoginMessages.EXISTED_USERNAME;
        if (inputs.get("password").equals("random")) {
            inputs.replace("password", inputs.get("password"), SignupAndLoginUtils.generateRandomPassword());
            return SignupAndLoginMessages.CONFIRM;
        }
        User newUser = new User(inputs.get("username"), inputs.get("password"), inputs.get("email"),
                inputs.get("nickname"), inputs.get("slogan"));
        Stronghold.addUser(newUser);
        return SignupAndLoginMessages.SUCCESS;
    }


    public SignupAndLoginMessages pickQuestion(HashMap<String, String> inputs) {
        int number = Integer.parseInt(inputs.get("questionNumber"));
        if (!inputs.get("answer").equals(inputs.get("answerConfirm")) || (number < 1 || number > 3))
            return SignupAndLoginMessages.FAIL;
        Pair pair;
        User user = Stronghold.getUserByUsername(inputs.get("username"));
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
        return SignupAndLoginMessages.SUCCESS;
    }

    public SignupAndLoginMessages login(HashMap<String, String> inputs) {
        if (inputs.get("username") == null || inputs.get("password") == null)
            return SignupAndLoginMessages.EMPTY_FIELD;
        if (Stronghold.getUserByUsername(inputs.get("username")) == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        if (this.loginTime != null && LocalDateTime.now().isBefore(loginTime))
            return SignupAndLoginMessages.TOO_MANY_ATTEMPTS;
        if (!Stronghold.getUserByUsername(inputs.get("username")).isPasswordCorrect(inputs.get("password"))) {
            increaseFailedAttempts();
            return SignupAndLoginMessages.INCORRECT_PASSWORD;
        }
        failedAttemptsReset();
        return SignupAndLoginMessages.SUCCESS;
        //TODO enter to the main menu *>Diba
    }

    public SignupAndLoginMessages getCurrentUser(String username) {
        currentUser = Stronghold.getUserByUsername(username);
        if (currentUser == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        return SignupAndLoginMessages.SUCCESS;
    }

}
