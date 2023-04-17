package view;

import controller.SignupAndLoginController;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SignupAndLoginMenu {
    private final SignupAndLoginController controller = new SignupAndLoginController();

    public void run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            if ((matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.CREATEUSER)) != null){
                createUserCall(matcher);
            }
        }
    }
    private void createUserCall(Matcher matcher) {
        HashMap<String,String> inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATEUSER.getRegex());
        controller.signup(inputs);
    }
}

