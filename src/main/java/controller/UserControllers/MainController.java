package controller.UserControllers;

import model.User.User;

public class MainController {
    public User currentUser;

    public MainController(User currentUser) {
        this.currentUser = currentUser;
    }

}