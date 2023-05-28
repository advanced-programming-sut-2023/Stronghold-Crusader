package controller.UserControllers;

import controller.MapControllers.MapSelectController;
import model.User.User;
import model.User.UserManager;
import view.UserMenus.MainMenu;

public class MainController {
    User currentUser;

    MainController(User currentUser) {
        this.currentUser = currentUser;
    }

}