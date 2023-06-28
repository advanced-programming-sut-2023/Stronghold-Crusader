package controller.UserControllers;

import model.Stronghold;
import model.User.User;
import model.User.UserManager;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class LoginController {
    private final Stronghold stronghold = Stronghold.getInstance();
    public User currentUser = null;
    private int failedAttempts = 0;
    private LocalDateTime loginTime = null;
    public boolean loggedInProperty = false;

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

    public SignupAndLoginMessage login(HashMap<String, String> inputs) {
        currentUser = stronghold.getUser(inputs.get("username"));
        if (inputs.get("username").equals("") || inputs.get("password").equals(""))
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
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

    public SignupAndLoginMessage checkUserExist(String username) {
        currentUser = stronghold.getUser(username);
        if (currentUser == null)
            return SignupAndLoginMessage.USER_DOES_NOT_EXIST;
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}
