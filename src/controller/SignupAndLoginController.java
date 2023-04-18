package controller;

import model.Stronghold;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;

public class SignupAndLoginController {

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        if (inputs.get("password") == null || inputs.get("email") == null
                || inputs.get("email") == null || inputs.get("slogan") == "")
            return SignupAndLoginMessages.EMPTYFIELD;
        if (!FormatValidation.isFormatValid(inputs.get("username"), FormatValidation.USERNAME))
            return SignupAndLoginMessages.INVALIDUSERNAMEFORAMT;
        if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORDLENGTH))
            return SignupAndLoginMessages.PASSWORDWEEK_LENGTH;
        if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORDLETTERS))
            return SignupAndLoginMessages.PASSWORDWEEK_LETTERSPROBLEM;
        if (!FormatValidation.isFormatValid(inputs.get("email"), FormatValidation.EMAIL))
            return SignupAndLoginMessages.INVALIDEMAILFORMAT;
        if (!inputs.get("password").equals("random") && !inputs.get("password").equals(inputs.get("passwordConfirmation")))
            return SignupAndLoginMessages.CONFIRMATIONPASSWORDERROR;
        if (Stronghold.isEmailExist(inputs.get("email")))
            return SignupAndLoginMessages.EXISTEDEMAIL;
        if (Stronghold.getUserByUsername(inputs.get("username")) == null)
            return SignupAndLoginMessages.EXISTEDUSERNAME;
        return null;
    }
}
