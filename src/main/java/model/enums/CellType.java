package model.enums;

public enum CellType {
    FIELD("field"), GRAVEL("graval"), STONE("stone"),
    SLATE("slate"), IRON("iron"), GRASS("grass"),
    MEADOW("meadow"), DENSE_MEADOW("dense meadow"),
    OIL("oil"), PlAIN("plain"), SHALLOW_WATER("shallow water"),
    RIVER("river"), SMALL_POOL("small pool"), BIG_POOL("big pool"),
    BEACH("beach"), SEA("sea");
    private String name;
    CellType(String name){
        this.name = name;
    }
    public static CellType getType(String typeName){
        for(CellType type : CellType.values()){
            if(type.name.equals(typeName)) return type;
        }
        return null;
    }
}
