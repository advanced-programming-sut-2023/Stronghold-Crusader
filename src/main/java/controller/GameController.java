package controller;

import model.Game;
import model.User;
import view.GameMenu;

public class GameController {
    private User currentUser;
    private Game game;
    public GameController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        GameMenu gameMenu = new GameMenu(this);
        while(true){
            switch (gameMenu.run()){
                case "modifyMap":
                    modifyMap();
                    break;
            }
        }
    }

    private void modifyMap(){
        MapMakerController mapMakerController = new MapMakerController(currentUser, game.getMap());
        mapMakerController.run();
    }
}
