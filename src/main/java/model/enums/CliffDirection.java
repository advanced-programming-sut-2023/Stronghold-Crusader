package model.enums;

public enum CliffDirection {
    SOUTH("s"),
    NORTH("n"),
    EAST("e"),
    WEST("w"),
    RANDOM("r");
    private final String name;
    CliffDirection(String name){
        this.name = name;
    }
    public static CliffDirection getDirection(String direction){
        for(CliffDirection d : CliffDirection.values()){
            if (d.name.equals(direction)) return d;
        }
        return null;
    }
}
