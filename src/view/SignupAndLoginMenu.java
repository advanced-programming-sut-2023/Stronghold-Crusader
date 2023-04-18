package view;

import controller.SignupAndLoginController;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommands;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SignupAndLoginMenu {
    private final SignupAndLoginController controller = new SignupAndLoginController();

    public void run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            if ((matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.CREATE_USER)) != null){
                createUserCall(matcher);
            }
        }
    }
    private void createUserCall(Matcher matcher) {
        HashMap<String,String> inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        SignupAndLoginMessages message = controller.signup(inputs);
        switch (message) {
         //   case EMPTYFIELD:
        }
    }
}

