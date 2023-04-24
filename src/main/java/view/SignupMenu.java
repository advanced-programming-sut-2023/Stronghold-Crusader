package view;

import controller.SignupController;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommands;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    private final SignupController signupController;
    private final Scanner scanner;

    public SignupMenu(SignupController signupController) {
        this.signupController = signupController;
        this.scanner = Menu.getScanner();
    }

    public String run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = scanner.nextLine();
            SignupAndLoginCommands typeOfCommand = SignupAndLoginCommands.getCommand(nextCommand);
            if (typeOfCommand == null) {
                SignupAndLoginMessages.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = SignupAndLoginCommands.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                case LOGOUT:
                    return "logout";
                case CREATE_USER:
                    createUserCall(matcher);
                    break;
                case LOGIN_MENU:
                    SignupAndLoginMessages.LOGIN_MENU.printMessage();
                    return "login menu";
            }
        }
    }

    private void createUserCall(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        signupController.changeNullSloganToEmpty(inputs);
        boolean randomSlogan = inputs.get("slogan") != null && inputs.get("slogan").equals("random");
        SignupAndLoginMessages message = signupController.signup(inputs);

        if (message.equals(SignupAndLoginMessages.EXISTING_USERNAME)) {
            message.printMessage();
            if (isUsernameSuggestionAccept(inputs))
                message = signupController.signup(inputs);
        }
        if (message.equals(SignupAndLoginMessages.RANDOM_PASSWORD)) {
            if (randomSlogan) {
                printRandomSlogan(inputs);
                randomSlogan = false;
            }
            randomPasswordSuggestion(inputs);
            message = signupController.signup(inputs);
        }
        message.printMessage();
        if (message.equals(SignupAndLoginMessages.SUCCESS_PROCESS)) {
            if (randomSlogan) printRandomSlogan(inputs);
            pickUpQuestion(inputs);
        }
    }

    private boolean isUsernameSuggestionAccept(HashMap<String, String> inputs) {
        String username = inputs.get("username");
        inputs.replace("username", username, SignupAndLoginUtils.generateRandomUsername(username));
        System.out.println("Do you want to continue  registration process with " + inputs.get("username") + "?");
        return (FormatValidation.isFormatValid(Menu.getScanner().nextLine(), FormatValidation.YES));
    }

    private void randomPasswordSuggestion(HashMap<String, String> inputs) {
        System.out.println("Your random password is: " + inputs.get("password"));
        System.out.print("Please re-enter your password here:\t");
        inputs.replace("passwordConfirmation", inputs.get("passwordConfirmation"), Menu.getScanner().nextLine());
    }

    private void printRandomSlogan(HashMap<String, String> inputs) {
        System.out.println("your random slogan is: " + inputs.get("slogan"));
    }

    private void pickUpQuestion(HashMap<String, String> inputs) {
        SignupAndLoginMessages message = SignupAndLoginMessages.FAIL_PICKING_UP_QUESTION;
        do {
            SignupAndLoginMessages.PICKING_QUESTION.printMessage();
            String nextCommand = Menu.getScanner().nextLine();
            Matcher matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.QUESTION_PICK);
            if (matcher == null)
                System.out.println("some error founds");
            else {
                HashMap<String, String> pickQuestionInputs =
                        SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.QUESTION_PICK.getRegex());
                pickQuestionInputs.put("username", inputs.get("username"));
                message = signupController.pickQuestion(pickQuestionInputs);
                message.printMessage();
            }
        } while (!message.equals(SignupAndLoginMessages.SUCCESS_CREATING_USER));
    }

}
