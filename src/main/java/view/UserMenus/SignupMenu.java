package view.UserMenus;

import controller.UserControllers.SignupController;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.UserCommand.SignupAndLoginCommand;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

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
            SignupAndLoginCommand typeOfCommand = SignupAndLoginCommand.getCommand(nextCommand);
            if (typeOfCommand == null) {
                SignupAndLoginMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = SignupAndLoginCommand.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                case EXIT:
                    return "exit";
                case CREATE_USER:
                    createUserCall(matcher);
                    break;
                case LOGIN_MENU:
                    SignupAndLoginMessage.LOGIN_MENU.printMessage();
                    return "login menu";
            }
        }
    }

    private void createUserCall(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        signupController.changeNullSloganToEmpty(inputs);
        boolean randomSlogan = inputs.get("slogan") != null && inputs.get("slogan").equals("random");
        SignupAndLoginMessage message = signupController.signup(inputs);

        if (message.equals(SignupAndLoginMessage.EXISTING_USERNAME)) {
            message.printMessage();
            if (isUsernameSuggestionAccept(inputs))
                message = signupController.signup(inputs);
        }
        if (message.equals(SignupAndLoginMessage.RANDOM_PASSWORD)) {
            if (randomSlogan) {
                printRandomSlogan(inputs);
                randomSlogan = false;
            }
            randomPasswordSuggestion(inputs);
            message = signupController.signup(inputs);
        }
        message.printMessage();
        if (message.equals(SignupAndLoginMessage.SUCCESS_PROCESS)) {
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
        SignupAndLoginMessage message = SignupAndLoginMessage.FAIL_PICKING_UP_QUESTION;
        do {
            SignupAndLoginMessage.PICKING_QUESTION.printMessage();
            String nextCommand = Menu.getScanner().nextLine();
            Matcher matcher = SignupAndLoginCommand.getMatcher(nextCommand, SignupAndLoginCommand.QUESTION_PICK);
            if (matcher == null)
                System.out.println("some error founds");
            else {
                HashMap<String, String> pickQuestionInputs =
                        SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.QUESTION_PICK.getRegex());
                pickQuestionInputs.put("username", inputs.get("username"));
                message = signupController.pickQuestion(pickQuestionInputs);
                message.printMessage();
            }
        } while (!message.equals(SignupAndLoginMessage.SUCCESS_CREATING_USER));
    }

}
