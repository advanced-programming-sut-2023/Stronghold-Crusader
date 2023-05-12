package controller.UserControllers;

import controller.MapControllers.MapSelectController;
import model.User.User;
import model.User.UserManager;
import view.UserMenus.MainMenu;
import view.enums.messages.UserMessage.MainMenuMessage;

public class MainController {
    User currentUser;

    MainController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        MainMenu mainMenu = new MainMenu(this);
        while (true) {
            switch (mainMenu.run()) {
                case "logout":
                    UserManager.setLoggedInUser(null);
                    return;
                case "newGame":
                    MapSelectController mapSelectController = new MapSelectController(currentUser);
                    mapSelectController.run();
                    break;
                case "profileMenu":
                    ProfileController profileController = new ProfileController(currentUser);
                    profileController.run();
                    break;
                case "exit":
                    return;
            }
        }
    }
}
