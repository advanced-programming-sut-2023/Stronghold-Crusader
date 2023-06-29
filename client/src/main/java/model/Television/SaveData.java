package model.Television;

import java.io.Serializable;

public class SaveData implements Serializable {
    private static int number = 0;
    private static int maxNumber;
    private static final long serialVersionID = 1L;
    public static int mapX;
    public static int mapY;

    public SaveData(int x, int y, String[][] map, String[][] buildings) {
        mapX = x;
        mapY = y;
        number++;
        this.map = map;
        this.buildings = buildings;
        maxNumber = number;
    }


    public String[][] map;
    public String[][] buildings;

    public static int getNumber() {
        return number;
    }

    public static void load() {
        number--;
    }

    public static int where() {
        load();
        return maxNumber - number;
    }
}

