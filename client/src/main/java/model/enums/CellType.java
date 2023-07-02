package model.enums;

import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;


public enum CellType {
    @SerializedName("1")
    FIELD("field"),
    @SerializedName("2")
    GRAVEL("gravel"),
    @SerializedName("3")
    STONE("stone"),
    @SerializedName("4")
    SLATE("slate"),
    @SerializedName("5")
    IRON("iron"),
    @SerializedName("6")
    GRASS("grass"),
    @SerializedName("7")
    MEADOW("meadow"),
    @SerializedName("8")
    DENSE_MEADOW("dense meadow"),
    @SerializedName("9")
    PlAIN("plain"),
    @SerializedName("10")
    OIL("oil"),
    @SerializedName("11")
    SHALLOW_WATER("shallow water"),
    @SerializedName("12")
    RIVER("river"),
    @SerializedName("13")
    SMALL_POOL("small pool"),
    @SerializedName("14")
    BIG_POOL("big pool"),
    @SerializedName("15")
    BEACH("beach"),
    @SerializedName("16")
    SEA("sea");
    private final String name;
    private final Image image;

    CellType(String name) {
        this.name = name;
        image = new Image(CellType.class.getResource("/assets/graphic/tiles/" + this.ordinal() + ".jpg").toExternalForm());
    }

    public static CellType getType(String typeName) {
        for (CellType type : CellType.values()) {
            if (type.name.equals(typeName)) return type;
        }
        return null;
    }

    public Image getImage() {
        return image;
    }

    public static boolean isTraversableByType(CellType type) {
        return type.equals(FIELD) || type.equals(GRAVEL) || type.equals(STONE) || type.equals(IRON) || type.equals(GRASS)
                || type.equals(MEADOW) || type.equals(DENSE_MEADOW) || type.equals(PlAIN) || type.equals(SHALLOW_WATER);
    }

    public String getName() {
        return name;
    }
    public static CellType getTypeBySerial(int i) {
        for (CellType type : CellType.values()) {
            if (type.ordinal() == i) {
                return type;
            }
        }
        return null;
    }
}
