package database;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapManager {

    public static void initializeMapFolder() {
        File resourceDir = new File(Settings.MAP_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
    }

    public static void saveMap(String mapString, String mapId){
        initializeMapFolder();
        String mapPath = Settings.MAP_PATH + mapId;
        try {
            File file = new File(mapPath);
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(mapPath);
            fileWriter.write(mapString);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadMap(String mapId) throws IOException {
        String mapPath = Settings.MAP_PATH + mapId;
        return new String(Files.readAllBytes(Paths.get(mapPath)), StandardCharsets.UTF_8);
    }
}
