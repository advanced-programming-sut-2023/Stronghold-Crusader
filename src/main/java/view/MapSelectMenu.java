package view;

import controller.MapSelectController;

import java.util.Scanner;

public class MapSelectMenu {
    private MapSelectController mapSelectController;
    private Scanner scanner;
    public MapSelectMenu(MapSelectController mapSelectController){
        this.mapSelectController = mapSelectController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        return null;
    }
}
