package model.Map;

import Settings.Settings;
import com.google.gson.*;
import utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("DataFlowIssue")
public class MapManager {
    public static Map load(String mapId) {
        Reader reader;
        try {
            String fileName = getFilenameFromMapId(mapId);
            if (fileName == null)
                return null;
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(reader, Map.class);
    }

    // all the non-savable assets should be removed before calling this
    public static void save(Map map, String mapId) {
        try {
            FileWriter fileWriter = new FileWriter(generateMapAddress(mapId, map.getName(), map.getPlayerCount()));
            Gson gson = new GsonBuilder().serializeNulls().create();
            fileWriter.write(gson.toJson(map));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ArrayList<String>> getMapList() {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        File mapsDir = new File(Settings.MAPS_PATH);
        Pattern pattern = Pattern.compile("(?<mapId>\\d+)_(?<mapName>[\\S ]+)_(?<players>\\d+)\\.map");
        for (String fileName : mapsDir.list()) {
            Matcher matcher = pattern.matcher(fileName);
            matcher.find();
            ArrayList<String> mapInfo = new ArrayList<>();
            mapInfo.add(matcher.group("mapId"));
            mapInfo.add(matcher.group("mapName"));
            mapInfo.add(matcher.group("players"));
            list.add(mapInfo);
        }
        return list;
    }

    private static String generateMapAddress(String mapId, String mapName, int playersCount) {
        return Settings.MAPS_PATH + mapId + '_' + mapName + '_' + playersCount + ".map";
    }


    private static String getFilenameFromMapId(String mapId) {
        File mapsDir = new File(Settings.MAPS_PATH);
        for (String fileName : mapsDir.list()) {
            if (fileName.startsWith(mapId))
                return Settings.MAPS_PATH + fileName;
        }
        return null;
    }

    public static boolean isMapIDValid(String mapId){
        // TODO
        return false;
    }
}
