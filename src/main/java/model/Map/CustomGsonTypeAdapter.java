package model.Map;

import com.google.gson.*;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.StorageBuilding;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.Tree;
import model.enums.AssetType.MapAssetType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomGsonTypeAdapter implements JsonDeserializer<ArrayList<MapAsset>> {
    private static final HashMap<String, Class> classes = new HashMap<>();

    static {
        classes.put(MapAssetType.HEADQUARTER.name(), Building.class);
        classes.put(MapAssetType.STORE_HOUSE.name(), StorageBuilding.class);
        classes.put(MapAssetType.TREE.name(), Tree.class);
        classes.put(MapAssetType.CLIFF.name(), Cliff.class);
    }

    @Override
    public ArrayList<MapAsset> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<MapAsset> list = new ArrayList<>();
        JsonArray ja = json.getAsJsonArray();
        for (JsonElement je : ja) {
            String type = je.getAsJsonObject().get("type").getAsString();
            Class c = classes.get(type);
            if (c == null)
                throw new RuntimeException("Unknown class: " + type);
            list.add(context.deserialize(je, c));
        }
        return list;
    }
}
