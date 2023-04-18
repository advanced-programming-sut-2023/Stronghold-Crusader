package view;

import controller.SignupAndLoginController;
import utils.FormatValidation;
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
        switch (message){
            case CONFIRM:
            System.out.println("Your random password is: "+ inputs.get("password"));
            System.out.print("Please re-enter your password here:\t");
            inputs.replace("passwordConfirmation", inputs.get("passwordConfirmation"), Menu.getScanner().nextLine());
            message = controller.signup(inputs);
            break;
            case EXISTED_USERNAME:
                System.out.println("The username already taken");
                String username = inputs.get("username");
                inputs.replace("username", username, SignupAndLoginUtils.generateRandomUsername(username));
                System.out.println("Do you want to continue  registration process with "+ username +"?" );
                if (FormatValidation.isFormatValid(Menu.getScanner().nextLine(), FormatValidation.YES ))
                    message = controller.signup(inputs);
        }
        switch (message) {
            case EMPTY_FIELD:
                System.out.println("There are empty fields among the entered entries");
                break;
            case INVALID_USERNAME_FORMAT:
                System.out.println("Username format is not valid");
                break;
            case PASSWORD_WEEK_LENGTH:
                System.out.println("Password is week(password must has at least 6 characters)");
                break;
            case PASSWORD_WEEK_LETTERS_PROBLEM:
                System.out.println("Password is week(password must include at least 1 lowercase character" +
                        ", 1 uppercase character, 1 number, and 1 special character)");
                break;
            case INVALID_EMAIL_FORMAT:
                System.out.println("Email format is not valid");
                break;
            case CONFIRMATION_PASSWORD_ERROR:
                System.out.println("Passwords do not match");
                break;
            case EXISTED_EMAIL:
                System.out.println("The email already taken");
                break;
        }
    }
}

