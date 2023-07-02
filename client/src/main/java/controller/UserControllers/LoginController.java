package controller.UserControllers;

import model.Stronghold;
import model.User.User;
import model.User.UserManager;
import network.Connection;
import network.Request;
import utils.FormatValidation;
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
        if (!currentUser.isPasswordCorrect(inputs.get("password"))) {
            increaseFailedAttempts();
            return SignupAndLoginMessage.INCORRECT_PASSWORD;
        }
        Request request = new Request();
        request.setType("connect");
        request.setCommand("login");
        request.addParameter("username", currentUser.getUsername());
        String response = Connection.getInstance().sendRequest(request);
        if(response.equals("400: Already logged in"))
            return SignupAndLoginMessage.ALREADY_LOGGED_IN;
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

    public SignupAndLoginMessage changePassword(String answer , String newPass){
        if (!currentUser.isRecoveryPasswordCorrect(answer)) return SignupAndLoginMessage.INCORECT_RECOVERY_ANSWER;
        else if (!FormatValidation.isPasswordValid(newPass).equals(SignupAndLoginMessage.SUCCESS_PROCESS))
            return SignupAndLoginMessage.WEEK_SIMPLE_PASSWORD;
        currentUser.setPassword(newPass);
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}
