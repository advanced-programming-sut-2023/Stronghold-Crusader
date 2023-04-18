package view;

import controller.SignupAndLoginController;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommands;
import view.enums.messages.SignupAndLoginMessages;

import java.lang.invoke.VarHandle;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.regex.Matcher;

public class SignupAndLoginMenu {
    private final SignupAndLoginController controller = new SignupAndLoginController();

    public void run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            if ((matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.CREATE_USER)) != null)
                createUserCall(matcher);
            else if ((matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.LOGIN)) != null)
                loginCall(matcher);
            else if ((matcher = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.CHANGE_PASSWORD)) != null)
                changePasswordCall(matcher);
            else System.out.println("Invalid command");
        }
    }

    private void createUserCall(Matcher matcher) {
        HashMap<String, String> inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        boolean randomSlogan = inputs.get("slogan").equals("random");
        SignupAndLoginMessages message = controller.signup(inputs);
        switch (message) {
            case CONFIRM:
                if (randomSlogan) {
                    System.out.println("your random slogan is: " + inputs.get("slogan"));
                    randomSlogan = false;
                }
                System.out.println("Your random password is: " + inputs.get("password"));
                System.out.print("Please re-enter your password here:\t");
                inputs.replace("passwordConfirmation", inputs.get("passwordConfirmation"), Menu.getScanner().nextLine());
                message = controller.signup(inputs);
                break;
            case EXISTING_USERNAME:
                System.out.println("The username already taken");
                String username = inputs.get("username");
                inputs.replace("username", username, SignupAndLoginUtils.generateRandomUsername(username));
                System.out.println("Do you want to continue  registration process with " + inputs.get("username") + "?");
                if (FormatValidation.isFormatValid(Menu.getScanner().nextLine(), FormatValidation.YES))
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
            case CONFIRMATION_ERROR:
                System.out.println("Passwords do not match");
                break;
            case EXISTED_EMAIL:
                System.out.println("The email already taken");
                break;
            case SUCCESS:
                if (randomSlogan)
                    System.out.println("your random slogan is: " + inputs.get("slogan"));
                while (true) {
                    System.out.println("Pick your security question: 1. What is my father’s name?" +
                            " 2. What was my first pet’s name? 3. What is my mother’s last name?");
                    String nextCommand = Menu.getScanner().nextLine();
                    Matcher matcher2 = SignupAndLoginCommands.getMatcher(nextCommand, SignupAndLoginCommands.QUESTION_PICK);
                    if (matcher2 == null)
                        System.out.println("some error founds");
                    else {
                        HashMap<String, String> pickQuestionInputs =
                                SignupAndLoginUtils.getInputs(matcher2, SignupAndLoginCommands.QUESTION_PICK.getRegex());
                        pickQuestionInputs.put("username", inputs.get("username"));
                        message = controller.pickQuestion(pickQuestionInputs);
                        if (!message.equals(SignupAndLoginMessages.FAIL)) break;
                        System.out.println("some error founds");
                    }
                }
                System.out.println("user created successfully");
                break;
        }
    }

    private void loginCall(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.LOGIN.getRegex());
        SignupAndLoginMessages messages = controller.login(inputs);
        switch (messages) {
            case EMPTY_FIELD:
                System.out.println("There are empty fields among the entered entries");
                break;
            case USER_DOES_NOT_EXIST:
                System.out.println("There is no user with this username");
                break;
            case INCORRECT_PASSWORD:
                System.out.println("The password entered is incorrect");
                break;
            case TOO_MANY_ATTEMPTS:
                int minutes = (int) (controller.getTimeUntilLogin() / 60);
                int seconds = (int) (controller.getTimeUntilLogin() % 60);
                System.out.println("Too many failed attempts. Please wait " + minutes + " minutes and " +
                        seconds + " seconds before trying again");
            case SUCCESS:
                //TODO go to the main menu >*Diba
                break;
        }
    }

    private void changePasswordCall(Matcher matcher) {
        SignupAndLoginMessages messages = controller.getCurrentUser(matcher.group("username"));
        if (messages.equals(SignupAndLoginMessages.USER_DOES_NOT_EXIST))
            System.out.println("There is no user with this username");
        else {
            String nextCommand = Menu.getScanner().nextLine();
            System.out.println(controller.currentUser.getPasswordRecoveryQuestion());
            String recoveryAnswer = nextCommand;
            if (controller.currentUser.isRecoveryPasswordCorrect(recoveryAnswer)) {
                while (true) {
                    System.out.println("Enter new Password:");
                    nextCommand = Menu.getScanner().nextLine();
                    if (!FormatValidation.isFormatValid(nextCommand, FormatValidation.PASSWORD_LENGTH)) {
                        System.out.println("Password is week(password must has at least 6 characters)");
                    } else if (!FormatValidation.isFormatValid(nextCommand, FormatValidation.PASSWORD_LETTERS))
                        System.out.println("Pick your security question: 1. What is my father’s name?" +
                                " 2. What was my first pet’s name? 3. What is my mother’s last name?");
                    else {
                        controller.currentUser.changePassword(recoveryAnswer, nextCommand);
                        System.out.println("Password changed successfully");
                        break;
                    }
                }
            } else System.out.println("Incorrect answer!");
        }
    }
}

