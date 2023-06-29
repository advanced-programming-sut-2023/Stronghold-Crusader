package controller.UserControllers;

import model.User.User;
import view.UserMenus.MainMenu;

public class MainController {
    public static User currentUser;
    public MainMenu menu;

    public MainController(User user, MainMenu menu) {
        currentUser = user;
        this.menu = menu;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}