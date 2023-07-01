package model.Television;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {
    public static void save(Serializable data,String mapId, String filename) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("television", mapId,filename)))) {
            oos.writeObject(data);
        }

    }

    public static Object load(String mapId, String filename) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("television", mapId,filename)))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

}
