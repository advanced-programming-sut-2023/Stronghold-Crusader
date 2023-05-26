package view.UserMenus;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.UserCommand.SignupAndLoginCommand;
import view.enums.messages.UserMessage.SignupAndLoginMessage;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Application {
    private final LoginController loginController;
    private final Scanner scanner;

    public LoginMenu(LoginController loginController) {
        this.loginController = loginController;
        this.scanner = Menu.getScanner();
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
