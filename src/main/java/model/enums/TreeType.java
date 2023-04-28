package model.enums;

public enum TreeType {
    DESERT, CHERRY, OLIVE, COCONUT, DATE;
    public static TreeType getType(String treeType){
        for(TreeType type : TreeType.values()){
            if(type.name().toLowerCase().equals(treeType)) return type;
        }
        return null;
    }
}
