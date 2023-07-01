package model.Television;

import java.io.Serializable;

public class SaveData implements Serializable {
    private static int number = 0;
    private static final long serialVersionID = 1L;
    public static int mapX;
    public static int mapY;

    public String mapId;
    public String[][] buildings;

    public SaveData(int x, int y, String mapId, String[][] buildings) {
        mapX = x;
        mapY = y;
        number++;
        //TODO MapId Method to save logs
        this.mapId = mapId;
        this.buildings = buildings;
    }



    public static int getNumber() {
        return number;
    }

    public static void load() {
        number--;
    }

}

