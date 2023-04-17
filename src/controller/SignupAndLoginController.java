package controller;

import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;

public class SignupAndLoginController {

    public SignupAndLoginMessages signup(HashMap<String, String> inputs) {
        for (String name : inputs.keySet()) {
            System.out.println(inputs.get(name));
        }
        return null;
    }
}
