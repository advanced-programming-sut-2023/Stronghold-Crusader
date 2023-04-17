package controller;

import utils.SignupAndLoginUtils;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;

public class SignupAndLoginController {

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        if (inputs.get("password") == null || inputs.get("email") == null || inputs.get("email") == null)
            return SignupAndLoginMessages.EMPTYFIELD;
        //if (!SignupAndLoginUtils.isUsernameForamtCorrect(inputs.get("username")))
         //   return SignupAndLoginMessages.
        return null;
    }
}
