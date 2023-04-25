package controller;

import model.User;
import view.MainMenu;

public class MainController {
    User currentUser;
    MainController(User currentUser){
        this.currentUser = currentUser;
    }
    public void run(){
        MainMenu mainMenu = new MainMenu(this);
        while(true){
            switch (mainMenu.run()){
                case "logout":
                    return;
                case "newGame":
                    MapSelectController mapSelectController = new MapSelectController(currentUser);
                    mapSelectController.run();
                    break;
                case "profileMenu":
                    ProfileController profileController = new ProfileController(currentUser);
                    profileController.run();
                    break;
                case "mapMakerMenu":
                    MapMakerController mapMakerController = new MapMakerController(currentUser);
                    mapMakerController.run();
                    break;

            }
        }
    }
}
