package controller;

import model.Stronghold;
import model.User;
import view.LoginMenu;
import view.enums.messages.SignupAndLoginMessages;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class LoginController {
    public User currentUser = null;
    private int failedAttempts = 0;
    private LocalDateTime loginTime = null;
    private final Stronghold stronghold = Stronghold.getInstance();
    private MainController mainController;
    private SignupController signupController;
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
        LoginMenu loginMenu = new LoginMenu(this);
        while (true) {
            switch (loginMenu.run()) {
                //TODO @kian we should type exit to quit, not logout
                case "logout":
                    return;
                case "signup menu":
                    signupController = new SignupController();
                    signupController.run();
                    return;
                case "login":
                    mainController.run();
                    break;
            }
        }
    }
    public SignupAndLoginMessages login(HashMap<String, String> inputs) {
        currentUser = Stronghold.getInstance().getUser(inputs.get("username"));
        if (inputs.get("username") == null || inputs.get("password") == null)
            return SignupAndLoginMessages.EMPTY_FIELD;
        if (currentUser == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        if (this.loginTime != null && LocalDateTime.now().isBefore(loginTime))
            return SignupAndLoginMessages.TOO_MANY_ATTEMPTS;
        if (!Stronghold.getInstance().getUser(inputs.get("username")).isPasswordCorrect(inputs.get("password"))) {
            increaseFailedAttempts();
            return SignupAndLoginMessages.INCORRECT_PASSWORD;
        }
        failedAttemptsReset();
        mainController = new MainController(currentUser);
        return SignupAndLoginMessages.SUCCESS_PROCESS;
    }
    public SignupAndLoginMessages getCurrentUser(String username) {
        currentUser = stronghold.getUser(username);
        if (currentUser == null)
            return SignupAndLoginMessages.USER_DOES_NOT_EXIST;
        return SignupAndLoginMessages.SUCCESS_PROCESS;
    }

}
