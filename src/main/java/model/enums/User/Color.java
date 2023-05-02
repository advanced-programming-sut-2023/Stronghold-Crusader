package model.enums.User;

public enum Color {
    RED, ORANGE, BLUE_LIGHT, BLUE, YELLOW, GRAY, PURPLE, GREEN;
    public static Color getColor(String colorName){
        for(Color color : Color.values()){
            if (color.name().equalsIgnoreCase(colorName)) return color;
        }
        return null;
    }
}
