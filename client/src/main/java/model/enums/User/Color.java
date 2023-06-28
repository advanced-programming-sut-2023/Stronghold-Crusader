package model.enums.User;

public enum Color {
    RED, BLUE, GREEN, ORANGE, BLUE_LIGHT, PURPLE, YELLOW, GRAY;

    public static Color getColorWithSizeCheck(String colorName) {
        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(colorName)) return color;
        }
        return null;
    }

    public static Color getColorWithSizeCheck(String colorName, int mapSize) {
        Color[] colors = Color.values();
        for (int i = 0; i < mapSize; i++)
            if (colors[i].name().equalsIgnoreCase(colorName)) return colors[i];
        return null;
    }
}
