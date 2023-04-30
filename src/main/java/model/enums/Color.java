package model.enums;

public enum Color {
    RED, ORANGE, BLUE_LIGHT, BLUE, YELLOW, GRAY, PURPLE, GREEN;
    public static Color getColor(String colorName){
        for(Color color : Color.values()){
            if (color.name().toLowerCase().equals(colorName)) return color;
        }
        return null;
    }
}
