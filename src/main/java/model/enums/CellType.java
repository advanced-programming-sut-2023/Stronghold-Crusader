package model.enums;

import Settings.AnsiEscapeCodes;
import com.google.gson.annotations.SerializedName;

public enum CellType {
    @SerializedName("1")
    FIELD("field", AnsiEscapeCodes.YELLOW_BACKGROUND),
    @SerializedName("2")
    GRAVEL("gravel", AnsiEscapeCodes.YELLOW_BACKGROUND_BRIGHT),
    @SerializedName("3")
    STONE("stone", AnsiEscapeCodes.WHITE_BACKGROUND),
    @SerializedName("4")
    SLATE("slate", AnsiEscapeCodes.CYAN_BACKGROUND_BRIGHT),
    @SerializedName("5")
    IRON("iron", AnsiEscapeCodes.WHITE_BACKGROUND_BRIGHT),
    @SerializedName("6")
    GRASS("grass", AnsiEscapeCodes.GREEN_BACKGROUND_BRIGHT),
    @SerializedName("7")
    MEADOW("meadow", AnsiEscapeCodes.PURPLE_BACKGROUND),
    @SerializedName("8")
    DENSE_MEADOW("dense meadow", AnsiEscapeCodes.PURPLE_BACKGROUND_BRIGHT),
    @SerializedName("9")
    PlAIN("plain", AnsiEscapeCodes.GREEN_BACKGROUND),
    @SerializedName("10")
    OIL("oil", AnsiEscapeCodes.BLACK_BACKGROUND),
    @SerializedName("11")
    SHALLOW_WATER("shallow water", AnsiEscapeCodes.BLUE_BACKGROUND),
    @SerializedName("12")
    RIVER("river", AnsiEscapeCodes.BLUE_BACKGROUND),
    @SerializedName("13")
    SMALL_POOL("small pool", AnsiEscapeCodes.BLUE_BACKGROUND),
    @SerializedName("14")
    BIG_POOL("big pool", AnsiEscapeCodes.BLUE_BACKGROUND),
    @SerializedName("15")
    BEACH("beach", AnsiEscapeCodes.BLUE_BACKGROUND),
    @SerializedName("16")
    SEA("sea", AnsiEscapeCodes.BLUE_BACKGROUND);
    private final String name;
    private final String asniColor;

    CellType(String name, String asniColor) {
        this.asniColor = asniColor;
        this.name = name;
    }

    public static CellType getType(String typeName) {
        for (CellType type : CellType.values()) {
            if (type.name.equals(typeName)) return type;
        }
        return null;
    }

    public static boolean isTraversableByType(CellType type) {
        return type.equals(FIELD) || type.equals(GRAVEL) || type.equals(STONE) || type.equals(IRON) || type.equals(GRASS)
                || type.equals(MEADOW) || type.equals(DENSE_MEADOW) || type.equals(PlAIN) || type.equals(SHALLOW_WATER);
    }

    public String getAsniColor() {
        return asniColor;
    }

    public String getName() {
        return name;
    }
}
