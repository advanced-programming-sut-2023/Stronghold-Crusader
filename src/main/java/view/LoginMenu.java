package view;

import controller.LoginController;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommand;
import view.enums.messages.SignupAndLoginMessage;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private final LoginController loginController;
    private final Scanner scanner;

    public LoginMenu(LoginController loginController) {
        this.loginController = loginController;
        this.scanner = Menu.getScanner();
    }

    public String run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = scanner.nextLine();
            SignupAndLoginCommand typeOfCommand = SignupAndLoginCommand.getCommand(nextCommand);
            if (typeOfCommand == null) {
                SignupAndLoginMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = SignupAndLoginCommand.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                case LOGOUT:
                    return "logout";
                case LOGIN:
                    if (loginCall(matcher)) return "login";
                    break;
                case CHANGE_PASSWORD:
                    changePasswordCall(matcher);
                    break;
                case SIGNUP_MENU:
                    SignupAndLoginMessage.SIGNUP_MENU.printMessage();
                    return "signup menu";
            }
        }
    }

    private boolean loginCall(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.LOGIN.getRegex());
        SignupAndLoginMessage message = loginController.login(inputs);
        message.printMessage();
        if (message.equals(SignupAndLoginMessage.TOO_MANY_ATTEMPTS)) {
            tooManyAttemptsError();
            return false;
        }
        if (message.equals(SignupAndLoginMessage.SUCCESS_PROCESS)) {
            System.out.println("success");
            return true;
        }
        return false;
    }

    private void changePasswordCall(Matcher matcher) {
        SignupAndLoginMessage message = loginController.getCurrentUser(matcher.group("username"));
        message.printMessage();
        String nextCommand = Menu.getScanner().nextLine();
        System.out.println(loginController.currentUser.getPasswordRecoveryQuestion());
        if (loginController.currentUser.isRecoveryPasswordCorrect(nextCommand)) {
            recoveryPassword();
        } else System.out.println("Incorrect answer!");
    }

    private void tooManyAttemptsError() {
        int minutes = (int) (loginController.getTimeUntilLogin() / 60);
        int seconds = (int) (loginController.getTimeUntilLogin() % 60);
        System.out.println("Too many failed attempts. Please wait " + minutes + " minutes and " +
                seconds + " seconds before trying again");
    }

    private void recoveryPassword() {
        String nextCommand;
        while (true) {
            System.out.println("Enter new Password:");
            nextCommand = Menu.getScanner().nextLine();
            if (!FormatValidation.isFormatValid(nextCommand, FormatValidation.PASSWORD_LENGTH)) {
                SignupAndLoginMessage.PASSWORD_WEEK_LENGTH.printMessage();
            } else if (!FormatValidation.isFormatValid(nextCommand, FormatValidation.PASSWORD_LETTERS))
                SignupAndLoginMessage.PASSWORD_WEEK_LETTERS_PROBLEM.printMessage();
            else {
                loginController.currentUser.setPassword(nextCommand);
                break;
            }
        }
    }
}
