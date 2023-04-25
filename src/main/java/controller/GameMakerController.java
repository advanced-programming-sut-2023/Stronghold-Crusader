package controller;


import model.User;
import view.GameMakerMenu;

public class GameMakerController {
    private User currentUser;
    public GameMakerController(User currentUser){
        this.currentUser = currentUser;
    }

    // choosing the map - choosing the players - assigning colors
    public void run(){
        GameMakerMenu gameMakerMenu = new GameMakerMenu(this);
        while(true){
            switch (gameMakerMenu.run()){

            }
        }
    }
}
