package model.enums.AssetType;

public enum BuildingCategory {
    DEFENSE_AND_ATTACK,
    PRODUCTION,
    TRAINING_AND_EMPLOYMENT,
    STORAGE,
    NORMAL,
    ENTRANCE;

    public static BuildingCategory getCategory(String categoryName){
        for (BuildingCategory category: BuildingCategory.values()){
            if (category.name().toLowerCase().equals(categoryName)) return category;
        }
        return null;
    }
}
