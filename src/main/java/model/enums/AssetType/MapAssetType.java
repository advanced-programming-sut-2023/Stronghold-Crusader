package model.enums.AssetType;

import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;
import model.enums.CellType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public enum MapAssetType {
    //Buildings
    @SerializedName("1")
    HEADQUARTER,
    @SerializedName("2")
    LOOKOUT_TOWER,
    @SerializedName("3")
    PERIMETER_TOWER,
    @SerializedName("4")
    DEFENSE_TOWER,
    @SerializedName("5")
    SQUARE_TOWER,

    @SerializedName("6")
    CIRCULAR_TOWER,

    @SerializedName("7")
    BEAR_FACTORY,

    @SerializedName("8")
    BAKERY,

    @SerializedName("9")
    WHEAT_FIELD,

    //TODO FIX
    @SerializedName("10")
    GRAIN_FIELD,
    @SerializedName("11")
    DIARY_FACTORY,
    @SerializedName("12")
    APPLE_GARDEN,
    @SerializedName("13")
    POLTURNER,
    @SerializedName("14")
    FLETCHER,
    @SerializedName("15")
    BLACKSMITH,
    @SerializedName("16")
    ARMOURER,
    @SerializedName("17")
    WOOD_CUTTER,
    @SerializedName("18")
    QUARRY,
    @SerializedName("19")
    SHORT_WALL,
    @SerializedName("20")
    WALL,
    @SerializedName("21")
    STAIRS,

    @SerializedName("22")
    PITCH_RIG,

    @SerializedName("23")
    IRON_MINE,
    @SerializedName("24")
    MILL,
    @SerializedName("25")
    INN,
    @SerializedName("26")
    KILLING_PIT,
    @SerializedName("27")
    SIEGE_TENT,
    @SerializedName("28")
    STABLE,
    @SerializedName("29")
    BARRACK,
    @SerializedName("30")
    MERCENARY_POST,
    @SerializedName("31")
    ENGINEER_GUILD,
    @SerializedName("32")
    CAGED_WARDOG,
    @SerializedName("33")
    CHURCH,
    @SerializedName("34")
    CATHEDRAL,
    @SerializedName("35")
    HOUSE,
    @SerializedName("35")
    SMALL_GATEHOUSE,
    @SerializedName("36")
    BIG_GATEHOUSE,
    @SerializedName("37")
    ARMOURY,
    @SerializedName("38")
    FOOD_STORAGE,

    @SerializedName("39")
    STORE_HOUSE,
    @SerializedName("40")
    STORE,
    @SerializedName("41")
    OX_TETHER,
    @SerializedName("42")
    DRAW_BRIDGE,
    @SerializedName("43")
    HAUNTING_GROUND,
    @SerializedName("44")
    TUNNELER_POST,

    //People
    WORKER, ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK, ARCHER_BOW, SLAVE, SLINGER, ASSASSIN,
    HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN,

    //Siege warfare
    BATTERING_RAM, CATAPULT, STABLE_CATAPULT, FIRE_CATAPULT, SIEGE_TOWER, MOBILE_SHIELD,
    DOG, COW,

    //Nature objects
    TREE, CLIFF;

    private final Image image;

    MapAssetType() {
        if (this.ordinal() <= 43)
            image = new Image(CellType.class.getResource("/assets/graphic/buildings/" + this.ordinal() + ".png").toExternalForm());
        else
            image = new Image(CellType.class.getResource("/assets/graphic/buildings/1" + ".png").toExternalForm());
    }

    public static MapAssetType getMapAssetType(String typeName) {
        for (MapAssetType type : MapAssetType.values())
            if (type.name().equalsIgnoreCase(typeName)) return type;
        return null;
    }

    public static MapAssetType getPerson(String input) {
        for (MapAssetType person : getPeople()) {
            if (person.name().equalsIgnoreCase(input))
                return person;
        }
        return null;
    }

    public static ArrayList<MapAssetType> getPeople() {
        return new ArrayList<>(List.of(ARCHER, CROSSBOWMAN, SPEARMAN, PIKEMAN, MACEMAN, SWORDSMAN, KNIGHT, TUNNELER, BLACK_MONK,
                ARCHER_BOW, SLAVE, SLINGER, ASSASSIN, HORSE_ARCHER, ARABIAN_SWORDSMAN, FIRE_THROWER, ENGINEER, LADDER_MAN));
    }

    public static ArrayList<MapAssetType> getBuildings() {
        return new ArrayList<>(List.of(LOOKOUT_TOWER, PERIMETER_TOWER, DEFENSE_TOWER, SQUARE_TOWER, CIRCULAR_TOWER, BEAR_FACTORY, BAKERY, WHEAT_FIELD,
                GRAIN_FIELD, DIARY_FACTORY, APPLE_GARDEN, POLTURNER, FLETCHER, BLACKSMITH, ARMOURER, WOOD_CUTTER, QUARRY, SHORT_WALL, WALL, STAIRS,
                PITCH_RIG, IRON_MINE, MILL, INN, KILLING_PIT, SIEGE_TENT, STABLE, BARRACK, MERCENARY_POST, ENGINEER_GUILD, CAGED_WARDOG,
                CHURCH, CATHEDRAL, HOUSE, SMALL_GATEHOUSE, BIG_GATEHOUSE, ARMOURY, FOOD_STORAGE, STORE_HOUSE, STORE, OX_TETHER, DRAW_BRIDGE, HAUNTING_GROUND));
    }

    public Image getImage() {
        return image;
    }
    public static MapAssetType getTypeBySerial(int i){
        for (MapAssetType type : MapAssetType.values()) {
            if (type.ordinal() == i){
                return type;
            }
        }
        return null;
    }
}
