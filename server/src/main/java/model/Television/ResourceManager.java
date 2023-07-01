package model.Television;

import database.Settings;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {
    public static void save(Serializable data, String gameId, String filename) throws Exception {
        initializeGameFolder(gameId);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("server", "database",
                "television", gameId, filename)))) {
            oos.writeObject(data);
        }
    }

    public static void initializeGameFolder(String gameId) {
        File resourceDir = new File(Settings.TELEVISION_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        File resourceDirForId = new File(Settings.TELEVISION_PATH + gameId);
        if (resourceDirForId.exists()) resourceDirForId.delete();
        resourceDirForId.mkdir();

    }


    public static Object load(String gameId, String filename) throws Exception {

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("server","database",
                "television", gameId, filename)))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

}
