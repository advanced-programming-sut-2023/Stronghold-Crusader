package controller;

import model.User;
import view.ProfileMenu;

public class ProfileController {
    User currentUser;
    public ProfileController(User currentUser){
        this.currentUser = currentUser;
    }

    public String run(){
        while(true){
            ProfileMenu profileMenu = new ProfileMenu(this);
            switch (profileMenu.run()){

            }
        }
    }
}
