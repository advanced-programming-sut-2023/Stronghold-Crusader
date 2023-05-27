package controller.UserControllers;

import model.Stronghold;
import model.User.User;
import model.enums.User.Slogan;
import utils.Captcha;
import utils.FormatValidation;
import utils.Pair;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.UserMenus.SignupMenu;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.util.HashMap;

public class SignupController {
    private final Stronghold stronghold = Stronghold.getInstance();

    public void run() {
        SignupMenu signupMenu = new SignupMenu(this);
        while (true) {
            switch (signupMenu.run()) {
                case "exit":
                    return;
                case "login menu":
                    LoginController loginController = new LoginController();
//                    loginController.run();
                    break;
            }
        }
    }

    public SignupAndLoginMessage signup(HashMap<String, String> inputs) {
        if (hasEmptyField(inputs))
            return SignupAndLoginMessage.EMPTY_FIELD;
        if (inputs.get("slogan").equals("random"))
            inputs.replace("slogan", inputs.get("slogan"), generateRandomSlogan());

        if (checkFormatOfInputs(inputs) != null)
            return checkFormatOfInputs(inputs);
        if (!inputs.get("password").equals("random") && !inputs.get("password").equals(inputs.get("passwordConfirmation")))
            return SignupAndLoginMessage.CONFIRMATION_ERROR;
        if (stronghold.emailExists(inputs.get("email")))
            return SignupAndLoginMessage.EXISTED_EMAIL;
        if (stronghold.userExists(inputs.get("username")))
            return SignupAndLoginMessage.EXISTING_USERNAME;
        if (inputs.get("password").equals("random")) {
            inputs.replace("password", inputs.get("password"), SignupAndLoginUtils.generateRandomPassword());
            return SignupAndLoginMessage.RANDOM_PASSWORD;
        }
        User newUser = new User(inputs.get("username"), inputs.get("password"), inputs.get("email"),
                inputs.get("nickname"), inputs.get("slogan"));
        stronghold.addUser(newUser);
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

    public SignupAndLoginMessage pickQuestion(HashMap<String, String> inputs) {
        int number = Integer.parseInt(inputs.get("questionNumber"));
        if (!inputs.get("answer").equals(inputs.get("answerConfirm")) || (number < 1 || number > 3))
            return SignupAndLoginMessage.FAIL_PICKING_UP_QUESTION;
        while (true) {
            System.out.println("Enter the code shown below :");
            Captcha.generateCaptcha();
            if (!Captcha.isFilledCaptchaValid(Menu.getScanner().nextLine()))
                return SignupAndLoginMessage.INVALID_CAPTCHA;
            else break;
        }
        Pair pair;
        User user = stronghold.getUser(inputs.get("username"));
        switch (number) {
            case 1:
                pair = new Pair("What is my father’s name?", inputs.get("answer"));
                break;
            case 2:
                pair = new Pair("What was my first pet’s name?", inputs.get("answer"));
                break;
            case 3:
                pair = new Pair("What is my mother’s last name?", inputs.get("answer"));
                break;
            default:
                pair = new Pair("", "");
                break;
        }
        user.setPasswordRecovery(pair);
        return SignupAndLoginMessage.SUCCESS_CREATING_USER;
    }

    private boolean hasEmptyField(HashMap<String, String> inputs) {
        return inputs.get("password") == null || inputs.get("email") == null
                || inputs.get("email") == null || inputs.get("slogan") == null;
    }

    private SignupAndLoginMessage checkFormatOfInputs(HashMap<String, String> inputs) {
        if (!FormatValidation.isFormatValid(inputs.get("username"), FormatValidation.USERNAME))
            return SignupAndLoginMessage.INVALID_USERNAME_FORMAT;

        if (!inputs.get("password").equals("random")) {
            if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LENGTH))
                return SignupAndLoginMessage.PASSWORD_WEEK_LENGTH;
            if (!FormatValidation.isFormatValid(inputs.get("password"), FormatValidation.PASSWORD_LETTERS))
                return SignupAndLoginMessage.PASSWORD_WEEK_LETTERS_PROBLEM;
        }

        if (!FormatValidation.isFormatValid(inputs.get("email"), FormatValidation.EMAIL))
            return SignupAndLoginMessage.INVALID_EMAIL_FORMAT;
        return null;
    }

    private String generateRandomSlogan() {
        return Slogan.values()[(int) (Math.random() * 5)].getSlogan();
    }

    public void changeNullSloganToEmpty(HashMap<String, String> inputs) {
        if (inputs.get("sloganTest") == null) inputs.replace("slogan", null, "");
    }

}
