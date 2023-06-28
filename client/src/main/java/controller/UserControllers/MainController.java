package controller.UserControllers;

import model.User.User;
import view.UserMenus.MainMenu;

public class MainController {
    public User currentUser;
    public MainMenu menu;

    public MainController(User currentUser, MainMenu menu) {
        this.currentUser = currentUser;
        this.menu = menu;
    }

}