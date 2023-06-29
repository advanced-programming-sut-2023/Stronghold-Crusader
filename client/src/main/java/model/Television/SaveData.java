package model.Television;

import java.io.Serializable;

public class SaveData implements Serializable {
    private static int number = 0;
    private static int maxNumber;
    private static final long serialVersionID = 1L ;
    public  int x;
    public int y;

    public SaveData(int x, int y, String[][] map, String[][] buildings) {
        this.x =x;
        this.y = y;
        number ++;
        this.map = map;
        this.buildings = buildings;
        maxNumber = number;
    }


    public String[][] map;
    public String[][] buildings;
    public String[][] mapAssets;

    public static int getNumber() {
        return number;
    }
    public static void load() {
        number --;
    }
    public static int where() {
        load();
       return maxNumber - number;
    }
}

