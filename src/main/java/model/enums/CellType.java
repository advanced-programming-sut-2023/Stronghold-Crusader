package model.enums;

import Settings.AnsiEscapeCodes;

public enum CellType {
    FIELD("field", AnsiEscapeCodes.CYAN_BACKGROUND),
    GRAVEL("gravel", AnsiEscapeCodes.YELLOW_BACKGROUND_BRIGHT),
    STONE("stone", AnsiEscapeCodes.WHITE_BACKGROUND),
    SLATE("slate", AnsiEscapeCodes.CYAN_BACKGROUND_BRIGHT),
    IRON("iron", AnsiEscapeCodes.WHITE_BACKGROUND_BRIGHT),
    GRASS("grass", AnsiEscapeCodes.GREEN_BACKGROUND_BRIGHT),
    MEADOW("meadow", AnsiEscapeCodes.PURPLE_BACKGROUND),
    DENSE_MEADOW("dense meadow", AnsiEscapeCodes.PURPLE_BACKGROUND_BRIGHT),
    OIL("oil", AnsiEscapeCodes.BLACK_BACKGROUND),
    PlAIN("plain", AnsiEscapeCodes.GREEN_BACKGROUND),
    SHALLOW_WATER("shallow water", AnsiEscapeCodes.BLUE_BACKGROUND),
    RIVER("river", AnsiEscapeCodes.BLUE_BACKGROUND),
    SMALL_POOL("small pool", AnsiEscapeCodes.BLUE_BACKGROUND),
    BIG_POOL("big pool", AnsiEscapeCodes.BLUE_BACKGROUND),
    BEACH("beach", AnsiEscapeCodes.BLUE_BACKGROUND),
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

    public String getAsniColor() {
        return asniColor;
    }
}
