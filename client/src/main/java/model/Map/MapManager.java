package model.Map;

import Settings.Settings;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.ConstantManager;
import model.MapAsset.Building.Building;
import model.MapAsset.MapAsset;
import model.chatRoom.Chat;
import model.enums.AssetType.MapAssetType;
import model.enums.CellType;
import network.Connection;
import network.Request;
import utils.Vector2D;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
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
        ArrayList<MapAsset> al = new ArrayList<>();
        Gson gson = new GsonBuilder().registerTypeAdapter(al.getClass(), new CustomGsonTypeAdapter()).create();
        return gson.fromJson(reader, Map.class);
    }

    // all the non-savable assets should be removed before calling this
    public static void save(Map map, String mapId) {
        try {
            FileWriter fileWriter = new FileWriter(generateMapAddress(mapId, map.getName(), map.getPlayerCount()));
            Gson gson = new Gson();
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
            if (matcher.matches())
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

    public static boolean isMapIDValid(String mapId) {
        ArrayList<ArrayList<String>> maps = getMapList();
        for (ArrayList<String> map : maps)
            if (map.get(0).equals(mapId)) return true;
        return false;
    }

    public static int getMapPlayerCount(String mapId){
        ArrayList<ArrayList<String>> maps = getMapList();
        for (ArrayList<String> map : maps)
            if (map.get(0).equals(mapId)) return Integer.parseInt(map.get(2));
        return -1;
    }

    public static void createSavableMap(Map map){
        int[][] cellOrdinals = new int[map.getSize().x][map.getSize().y];
        ArrayList[][] buildings = new ArrayList[map.getSize().x][map.getSize().y];
        for (int j=0; j<map.getSize().y; j++){
            for (int i=0; i<map.getSize().x; i++){
                cellOrdinals[i][j] = map.getCell(new Vector2D(i, j)).getType().ordinal();
                ArrayList<Integer> building = new ArrayList<>();
                for (MapAsset mapAsset : map.getCell(new Vector2D(i, j)).getAllAssets())
                    building.add(mapAsset.getType().ordinal());
                buildings[i][j] = building;
            }
        }
        String cellOrdinalGson = new Gson().toJson(cellOrdinals);
        String buildingGson = new Gson().toJson(buildings);
        String mapStr = "cellOrdinal:" + cellOrdinalGson + "buildingGson:" + buildingGson;
        String info = "playerCount:" + map.getPlayerCount() + "size:" + map.getSize().x + "-" + map.getSize().y;
        saveMapInServer(info + mapStr, map.getName());
    }

    public static void saveMapInServer(String map, String id){
        Request request = new Request();
        request.setType("map");
        request.setCommand("create_map");
        request.addParameter("map", map);
        request.addParameter("id", id);
        System.out.println(Connection.getInstance().sendRequest(request));
    }
    public static void convertSavableToMap(String id){
        Request request = new Request();
        request.setType("map");
        request.setCommand("load_map");
        request.addParameter("id", id);
        String mapStr = Connection.getInstance().sendRequest(request);
        Pattern pattern = Pattern.compile("playerCount:(?<count>\\S+)size:(?<x>\\S+)-(?<y>\\S+)cellOrdinal:(?<cellOrdinal>\\S+)buildingGson:(?<buildingGson>\\S+)");
        Matcher matcher = pattern.matcher(mapStr);
        matcher.find();
        int playerCount = Integer.parseInt(matcher.group("count"));
        int mapX = Integer.parseInt(matcher.group("x"));
        int mapY = Integer.parseInt(matcher.group("y"));
        String cellOrdinalGson = matcher.group("cellOrdinal");
        String buildingGson = matcher.group("buildingGson");
        Type arrayListType = new TypeToken<ArrayList[][]>() {
        }.getType();
        ArrayList[][] buildings = new Gson().fromJson(buildingGson, arrayListType);
        arrayListType = new TypeToken<int[][]>() {
        }.getType();
        int[][] cellOrdinals = new Gson().fromJson(cellOrdinalGson, arrayListType);
        Map map = new Map(new Vector2D(mapX, mapY), id);
        map.setPlayerCount(playerCount);
        setMapTexture(cellOrdinals, map);
        setBuildings(buildings, map);
    }

    private static void setMapTexture(int[][] cellOrdinals, Map map){
        for (int j=0; j<map.getSize().y; j++){
            for (int i=0; i<map.getSize().x; i++){
                CellType type = CellType.getTypeBySerial(cellOrdinals[i][j]);
                map.changeCellTypeTo(new Vector2D(i, j), type);
            }
        }
    }

    private static void setBuildings(ArrayList[][] buildings, Map map){
        for (int j=0; j<map.getSize().y; j++){
            for (int i=0; i<map.getSize().x; i++){
                Vector2D coordinate = new Vector2D(i, j);
                for (Object ordinal : buildings[i][j]){
                    map.addMapObject(coordinate, new Building((Building) ConstantManager.getInstance()
                            .getAsset(MapAssetType.getTypeBySerial((Integer) ordinal)), coordinate, null));
                }
            }
        }
    }
}
