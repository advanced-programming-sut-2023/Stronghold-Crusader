package model.Television;

import java.io.Serializable;


public class SaveData implements Serializable {
    private static int number = 0;
    private static final long serialVersionID = 1L;
    public static int mapX;
    public static int mapY;

    public String mapId;
    public String[][] assets;

    public SaveData(int x, int y, String mapId, String[][] buildings, String[][] people) {
        mapX = x;
        mapY = y;
        number++;
        this.mapId = mapId;
        this.assets = buildings;
      //  this.assets[1] = people;
    }




    public static int getNumber() {
        return number;
    }

    public static void load() {
        number--;
    }

}

