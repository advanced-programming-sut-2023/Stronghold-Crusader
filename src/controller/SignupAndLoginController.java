package controller;

import model.Slogan;
import model.Stronghold;
import model.User;
import utils.FormatValidation;
import utils.Pair;
import utils.SignupAndLoginUtils;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;

public class SignupAndLoginController {

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
}
