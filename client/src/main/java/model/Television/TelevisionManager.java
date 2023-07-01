package model.Television;

import com.google.gson.Gson;
import network.Connection;
import network.Request;

public class TelevisionManager {

    public static void save(SaveData saveData,String mapId, String filename) {
        Request request = new Request();
        request.setType("television");
        request.setCommand("save");
        request.addParameter("id", mapId );
        request.addParameter("saveData", new Gson().toJson(saveData));
        request.addParameter("filename", filename);
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) {
            try {
                throw new Exception("can't save this file");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static SaveData load(String gameId , String filename) {
        Request request = new Request();
        request.setType("television");
        request.setCommand("load");
        request.addParameter("id", gameId );
        request.addParameter("filename", filename);
        String result = Connection.getInstance().sendRequest(request);

        if (result.startsWith("400")) {
            try {
                throw new Exception("can't load this file");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().fromJson(result, SaveData.class);
    }
}
