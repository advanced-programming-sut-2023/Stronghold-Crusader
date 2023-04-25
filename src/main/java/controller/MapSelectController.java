package controller;


import model.User;
import view.MapSelectMenu;

public class MapSelectController {
    private User currentUser;
    public MapSelectController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        MapSelectMenu mapSelectMenu = new MapSelectMenu(this);
        while(true){
            switch (mapSelectMenu.run()){

            }
        }
    }
}
