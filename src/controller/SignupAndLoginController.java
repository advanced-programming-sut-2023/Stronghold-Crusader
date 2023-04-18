package controller;

import model.Stronghold;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;

public class SignupAndLoginController {

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        if (inputs.get("password") == null || inputs.get("email") == null
                || inputs.get("email") == null || inputs.get("slogan").equals(""))
            return SignupAndLoginMessages.EMPTY_FIELD;
        if (!FormatValidation.isFormatValid(inputs.get("username"), FormatValidation.USERNAME))
            return SignupAndLoginMessages.INVALID_USERNAME_FORMAT;
        if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LENGTH))
            return SignupAndLoginMessages.PASSWORD_WEEK_LENGTH;
        if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LETTERS))
            return SignupAndLoginMessages.PASSWORD_WEEK_LETTERS_PROBLEM;
        if (!FormatValidation.isFormatValid(inputs.get("email"), FormatValidation.EMAIL))
            return SignupAndLoginMessages.INVALID_EMAIL_FORMAT;
        if (!inputs.get("password").equals("random") && !inputs.get("password").equals(inputs.get("passwordConfirmation")))
            return SignupAndLoginMessages.CONFIRMATION_PASSWORD_ERROR;
        if (Stronghold.isEmailExist(inputs.get("email")))
            return SignupAndLoginMessages.EXISTED_EMAIL;
        if (Stronghold.getUserByUsername(inputs.get("username")) == null)
            return SignupAndLoginMessages.EXISTED_USERNAME;
        return null;
    }
}
