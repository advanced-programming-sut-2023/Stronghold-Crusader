package controller;

import model.Stronghold;
import model.User;
import model.UserManager;
import view.LoginMenu;
import view.enums.messages.SignupAndLoginMessage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class LoginController {
    private final Stronghold stronghold = Stronghold.getInstance();
    public User currentUser = null;
    private int failedAttempts = 0;
    private LocalDateTime loginTime = null;
    private MainController mainController;

    private void increaseFailedAttempts() {
        failedAttempts++;
        loginTime = LocalDateTime.now().plus(5 * (long) Math.pow(2, failedAttempts - 1), ChronoUnit.SECONDS);
    }

    private void failedAttemptsReset() {
        failedAttempts = 0;
        loginTime = null;
    }

    public long getTimeUntilLogin() {
        return LocalDateTime.now().until(this.loginTime, ChronoUnit.SECONDS);
    }

    public void run() {
        currentUser = UserManager.getLoggedInUser();
        if (currentUser != null) {
            mainController = new MainController(currentUser);
            mainController.run();
        }
        LoginMenu loginMenu = new LoginMenu(this);
        while (true) {
            switch (loginMenu.run()) {
                case "exit":
                    return;
                case "signup menu":
                    SignupController signupController = new SignupController();
                    signupController.run();
                    return;
                case "login":
                    mainController.run();
                    break;
            }
        }
    }

    public SignupAndLoginMessage login(HashMap<String, String> inputs) {
        currentUser = Stronghold.getInstance().getUser(inputs.get("username"));
        if (inputs.get("username") == null || inputs.get("password") == null)
            return SignupAndLoginMessage.EMPTY_FIELD;
        if (currentUser == null)
            return SignupAndLoginMessage.USER_DOES_NOT_EXIST;
        if (this.loginTime != null && LocalDateTime.now().isBefore(loginTime))
            return SignupAndLoginMessage.TOO_MANY_ATTEMPTS;
        if (!Stronghold.getInstance().getUser(inputs.get("username")).isPasswordCorrect(inputs.get("password"))) {
            increaseFailedAttempts();
            return SignupAndLoginMessage.INCORRECT_PASSWORD;
        }
        failedAttemptsReset();
        if (inputs.get("stayLoggedIn") != null) UserManager.setLoggedInUser(currentUser);
        mainController = new MainController(currentUser);
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

    public SignupAndLoginMessage checkUserExist(String username) {
        currentUser = stronghold.getUser(username);
        if (currentUser == null)
            return SignupAndLoginMessage.USER_DOES_NOT_EXIST;
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

}
