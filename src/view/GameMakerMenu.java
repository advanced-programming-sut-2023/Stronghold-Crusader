package view;

import controller.GameMakerController;
import controller.MainController;

import java.util.Scanner;

public class GameMakerMenu {
    private GameMakerController gameMakerController;
    private Scanner scanner;
    public GameMakerMenu(GameMakerController gameMakerController){
        this.gameMakerController = gameMakerController;
        this.scanner = Menu.getScanner();
    }
}
